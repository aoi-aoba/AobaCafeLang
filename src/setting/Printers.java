package setting;

import java.util.Map;

public class Printers {
    StringBuilder sb = null;

    public String print(String[] parts, Map<String, Integer> intVariables, Map<String, Long> longVariables) {
        sb = new StringBuilder();
        for(int i = 2; i < parts.length; i++) {
            String varName = parts[i];
            if(intVariables.containsKey(varName)) {
                sb.append(intVariables.get(varName));
            } else if(longVariables.containsKey(varName)) {
                sb.append(longVariables.get(varName));
            } else {
                sb = new StringBuilder();
                System.out.println("그 메뉴는 없어서 주문이 불가능하세요.");
                break;
            }
        }
        return sb.toString();
    }

    public String printWithASCII(String[] parts, Map<String, Integer> intVariables) {
        sb = new StringBuilder();
        for(int i = 5; i < parts.length; i++) {
            String varName = parts[i];
            if(intVariables.containsKey(varName)) {
                int temp = intVariables.get(varName);
                sb.append((char) temp);
            } else if(parts[i].equals("에요")) {
                break;
            } else {
                sb = new StringBuilder();
                System.out.println("그 메뉴는 없어서 주문이 불가능하세요.");
                break;
            }
        }
        return sb.toString();
    }
}
