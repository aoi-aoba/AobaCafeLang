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
                System.out.print("그 메뉴는 없어서 주문이 불가능하세요.");
                return null;
            }
            // 이렇게 주문할게요 구문 뒤에 여러 변수를 입력해서 모두 처리가 가능하게 설정
            // 존재하지 않는 변수를 입력하는 경우는 바로 return null을 반환하게 되어 있음(오류 처리됨)
        }
        return sb.toString();
    }

    public String printWithASCII(String[] parts, Map<String, Integer> intVariables) {
        sb = new StringBuilder();
        if(!parts[parts.length-1].equals("예요")) {
            System.out.print("주문이 틀리셨는데 DM으로 보내드린 메뉴판 다시 확인해주시고 주문하시겠어요?");
            return null;
        } // 예요 전까지의 모든 변수를 처리하기 위해 사용하는 문법으로, 없을 시 오류로 간주
        for(int i = 5; i < parts.length; i++) {
            String varName = parts[i];
            if(intVariables.containsKey(varName)) {
                int temp = intVariables.get(varName);
                sb.append((char) temp);
            } else if(parts[i].equals("예요")) {
                break;
            } else {
                System.out.print("그 메뉴는 없어서 주문이 불가능하세요.");
                return null;
            } // 해당 else 문에는 없는 변수도 들어가지만 long형 변수(그란데 사이즈)를 할당하려고 해도 동일하게 접근함
        }
        return sb.toString();
    }
}
