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

        int startPos = TotalText.indexOf("주문할게요");
        int endPos = TotalText.lastIndexOf("여기까지 해서 얼마에요");
        TotalText = TotalText.substring(startPos, endPos);

        DefaultFunctions func = new DefaultFunctions();

        String[] lines = TotalText.split("\\n");
        for(String line : lines) {
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
        reader.close();
    }

    public static List<String> findFiles(Path path, String fileExtension) throws IOException {
        if(!Files.isDirectory(path))
            throw new IllegalArgumentException("Path must be a directory!");
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