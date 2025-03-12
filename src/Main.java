import setting.DefaultFunctions;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("D:\\test.cafe");
        BufferedReader reader = new BufferedReader(fileReader);
        StringBuilder printText = new StringBuilder();
        // 파일 경로 입력 받기 (여기서는 메모장 파일)

        DefaultFunctions func = new DefaultFunctions();

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.equals("주문할게요")) { func.start(); }
            else if (line.equals("여기까지 해서 얼마에요")) { func.end(); }
            else if (func.checkInCodeBlock()) {
                line = line.trim();
                if(!func.doSomething(line)) {
                    printText = new StringBuilder();
                    reader.close();
                    return;
                } else {
                    printText.append(func.getSb());
                }
            }
        }

        System.out.println(printText);
        fileReader.close();
        reader.close();
    }
}