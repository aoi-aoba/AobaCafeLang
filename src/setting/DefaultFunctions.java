package setting;

import java.util.HashMap;
import java.util.Map;

public class DefaultFunctions {
    private Map<String, Long> longVariables = new HashMap<>();
    private Map<String, Integer> intVariables = new HashMap<>();

    String line;
    boolean isInCodeBlock; // 코드블록 내부가 맞는지 아닌지에 대한 여부를 체크할 수 있는 boolean 값 필드
    StringBuilder sb;

    Printers printer = new Printers();
    Calculators calc = new Calculators();

    public DefaultFunctions() { isInCodeBlock = false; } // constructor

    public void start() { this.isInCodeBlock = true; }
    public void end() { this.isInCodeBlock = false; }
    public boolean checkInCodeBlock() { return this.isInCodeBlock; }
    // 코드의 시작과 끝을 구별하는 선언이 있으므로 이를 활용하여 코드블록 내부를 구분, check할 수 있는 메소드까지 구현
    public String getter() { return sb.toString(); }

    public int doSomething(String input) {
        // 명령어를 판독하는 중심 분기문
        // doSomething 함수를 통해 -1 반환 시 exception, 0 반환 시 continue, 1 이상 반환 시 해당 줄로 goto 문 실행
        line = input;
        sb = new StringBuilder();
        if(line.startsWith("주문이")) { sb.append("\n"); }
        else if(line.startsWith("톨 사이즈로")) {
            String[] parts = line.split(" ");
            return makeVariable(parts, true) ? 0 : -1;
        } else if(line.startsWith("그란데 사이즈로")) {
            String[] parts = line.split(" ");
            return makeVariable(parts, false) ? 0 : -1;
        } else if(line.startsWith("이렇게 주문할게요")) {
            String[] parts = line.split(" ");
            if(parts[2].equals("캐리어에")) {
                String str = printer.printWithASCII(parts, intVariables);
                if(str == null) return -1;
                else sb.append(str);
            } else sb.append(printer.print(parts, intVariables, longVariables));
        } else if((line.endsWith("해주세요"))) {
            String[] parts = line.split(" ");
            calc.operation(parts, intVariables, longVariables);
        } else {
            System.out.print("주문이 틀리셨는데 DM으로 보내드린 메뉴판 다시 확인해주시고 주문하시겠어요?");
            return -1;
        }
        return 0;
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
            System.out.print("그 정도 양은 주문하신 사이즈에 드릴 수 없어요.");
            return false;
        }
    }
}
