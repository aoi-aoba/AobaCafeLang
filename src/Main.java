import setting.DefaultFunctions;
import setting.Items;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main extends Items {
    public static void main(String[] args) throws IOException {
        TotalText = "";
        StringBuilder printText = new StringBuilder();

        List<String> files = findFiles(Paths.get("D:\\cafelang"), "cafe");
        String fileName = files.get(0);
        BufferedReader reader = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8));
        String readerString;
        while((readerString = reader.readLine()) != null) first(readerString);
        reader.close();
        // filereader, bufferedReader 활용하여 .cafe 파일 받아오기 (여러개일 경우 처음만 받아옴)
        // findFiles 함수는 하단에 구현되어 있음

        int startPos = TotalText.indexOf("주문할게요");
        int endPos = TotalText.lastIndexOf("여기까지 해서 얼마에요");
        try { TotalText = TotalText.substring(startPos, endPos); }
        catch (StringIndexOutOfBoundsException e) {
            System.out.print("뒤에 손님들이 밀려서, 주문이 아직 정해지지 않으셨으면 결정하고 오시겠어요?");
            return;
        } // 시작 혹은 종료의 문구를 입력하지 않은 코드의 경우 Exception을 발생시키므로 해당 Exception을 catch 하여 문법 오류를 판단

        DefaultFunctions func = new DefaultFunctions();

        String[] lines = TotalText.split("\\n");
        for(int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.equals("주문할게요")) { func.start(); }
            else if (line.equals("여기까지 해서 얼마에요")) { func.end(); }
            else if (func.checkInCodeBlock()) {
                line = line.trim();
                int returnVal = func.doSomething(line, lines.length);
                if(returnVal == -1) {
                    reader.close();
                    return;
                } else if(returnVal == 0) {
                    printText.append(func.getter());
                } else {
                    i = returnVal - 2;
                }
            }
        }
        // enhenced for-loop를 사용하게 되면 goto문을 통해 이동하기 번거로움
        // lines[] 배열을 굳이 여러 번 parameter로 옮기면서 처리할 필요도 없어 보임
        // return value에 따라서 goto문 실행/오류처리까지 구분이 가능함

        System.out.print(printText);
        reader.close();
    }

    public static List<String> findFiles(Path path, String fileExtension) throws IOException {
        if(!Files.isDirectory(path))
            throw new IllegalArgumentException("경로가 폴더로 주어지지 않아 cafe 문서를 찾는 데 실패했습니다.");
        List<String> result;
        try (Stream<Path> walk = Files.walk(path)) {
            result = walk
                    .filter(p -> !Files.isDirectory(p))
                    .map(p -> p.toString().toLowerCase())
                    .filter(f -> f.endsWith(fileExtension))
                    .collect(Collectors.toList());
        }
        return result;
    }

    private static void first(String line) {
        TotalText += (line + "\n");
    }
}