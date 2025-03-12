package setting;

import java.util.HashMap;
import java.util.Map;

public class DefaultFunctions {
    private Map<String, Long> longVariables = new HashMap<>();
    private Map<String, Integer> intVariables = new HashMap<>();

    String line;
    boolean isInCodeBlock;
    StringBuilder sb;
    Printers printer = new Printers();

    public DefaultFunctions() {
        isInCodeBlock = false;
    }

    public void start() {
        this.isInCodeBlock = true;
    }

    public void end() {
        this.isInCodeBlock = false;
    }

    public String getSb() {
        return sb.toString();
    }

    public boolean checkInCodeBlock() {
        return this.isInCodeBlock;
    }

    public boolean doSomething(String input) {
        line = input;
        sb = new StringBuilder();
        if(line.startsWith("주문이")) {
            sb.append("\n");
        } else if(line.startsWith("톨 사이즈로")) {
            String[] parts = line.split(" ");
            return makeVariable(parts, true);
        } else if(line.startsWith("그란데 사이즈로")) {
            String[] parts = line.split(" ");
            return makeVariable(parts, false);
        } else if(line.startsWith("이렇게 주문할게요")) {
            String[] parts = line.split(" ");
            if(parts[2].equals("캐리어에")) {
                sb.append(printer.printWithASCII(parts, intVariables));
            } else sb.append(printer.print(parts, intVariables, longVariables));
        } else {
            return false;
        }
        return true;
    }

    public boolean makeVariable(String[] parts, boolean isInteger) {
        try {
            String varName = parts[2];
            if (isInteger) {
                int value = Integer.parseInt(parts[3].replace("ml", ""));
                intVariables.put(varName, value);
            } else {
                long value = Long.parseLong(parts[3].replace("ml", ""));
                longVariables.put(varName, value);
            }
            return true;
        } catch (ClassCastException | NumberFormatException e) {
            System.out.println("그 정도 양은 주문하신 사이즈에 드릴 수 없어요.");
            return false;
        }
    }
}
