package setting;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Calculators {
    Map<String, Integer> intVariables;
    Map<String, Long> longVariables;

    public boolean operation(String[] parts, Map<String, Integer> intVariables, Map<String, Long> longVariables) {
        String varName = parts[0];
        this.intVariables = intVariables;
        this.longVariables = longVariables;
        long temp = 0;
        if(parts[parts.length-1].equals("나눠주세요")) {
            if(!parts[1].equals("는") || !parts[3].equals("를") || !parts[5].equals("컵에")) {
                wrongCalculate();
                return false;
            } else temp = divide(parts, varName);
            if(temp == -1) return false;
        } else if(parts[parts.length-1].equals("넣어주세요")) {
            if(!parts[1].equals("는") || !parts[3].equals("를") || !parts[5].equals("배")) {
                wrongCalculate();
                return false;
            } else temp = multiply(parts, varName);
            if(temp == -1) return false;
        }
        else for(int i=1; i<parts.length; i++) {
            if(parts[i].equals("샷") && parts[i+1].equals("추가"))
                plusOne(varName);
            if(parts[i].equals("시럽") && parts[i+1].equals("빼고"))
                minusOne(varName);
        }
        return true;
    }

    public void plusOne(String varName) {
        if(intVariables.containsKey(varName))
            intVariables.replace(varName, intVariables.get(varName) + 1);
        else longVariables.replace(varName, longVariables.get(varName) + 1);
    }

    public void minusOne(String varName) {
        if(intVariables.containsKey(varName))
            intVariables.replace(varName, intVariables.get(varName) - 1);
        else longVariables.replace(varName, longVariables.get(varName) - 1);
    }

    public int divide(String[] parts, String varName) {
       try {
           long result = 0;
           String strTemp = parts[2];
           long dividend = 0;
           long divisor = Long.parseLong(parts[4]);
           if (strTemp.endsWith("ml")) {
               StringBuilder sb = new StringBuilder(strTemp);
               int startIndex = sb.indexOf("ml");
               sb.delete(startIndex, startIndex + "ml".length());
               dividend = Long.parseLong(sb.toString());
           } else if (intVariables.containsKey(strTemp)) dividend = intVariables.get(strTemp);
           else longVariables.get(strTemp);
           result = dividend / divisor;
           if (intVariables.containsKey(varName))
               if (result < Integer.MAX_VALUE) intVariables.replace(varName, (int) result);
               else {
                   wrongCalculate();
                   return -1;
               }
           else longVariables.replace(varName, result);
           return 1;
       } catch (Exception e) {
           wrongCalculate();
           return -1;
       }
    }

    public int multiply(String[] parts, String varName) {
        try {
            long result = 0;
            String strand = parts[2];
            String strier = parts[4];
            long multiplicand = 0, multiplier = 0;

            if (strand.endsWith("ml")) {
                StringBuilder sb = new StringBuilder(strand);
                int startIndex = sb.indexOf("ml");
                sb.delete(startIndex, startIndex + "ml".length());
                multiplicand = Long.parseLong(sb.toString());
            } else if (intVariables.containsKey(strand)) multiplicand = intVariables.get(strand);
            else multiplicand = longVariables.get(strand);

            if (intVariables.containsKey(strier)) multiplier = intVariables.get(strier);
            else if (longVariables.containsKey(strier)) multiplier = longVariables.get(strier);
            else multiplier = Integer.parseInt(strier);

            if(BigInteger.valueOf(multiplier).multiply(BigInteger.valueOf(multiplicand)).compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0) {
                wrongCalculate();
                return -1;
            } else result = multiplicand * multiplier;
            if (intVariables.containsKey(varName))
                if (result < Integer.MAX_VALUE) intVariables.replace(varName, (int) result);
                else {
                    wrongCalculate();
                    return -1;
                }
            else longVariables.replace(varName, result);
            return 1;
        } catch (Exception e) {
            wrongCalculate();
            return -1;
        }
    }

    public void wrongCalculate() {
        System.out.println("용량 계산이 잘못되어 인식되지 않는 주문이에요.");
    }
}
