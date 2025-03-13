package setting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Calculators {
    Map<String, Integer> intVariables;
    Map<String, Long> longVariables;

    public void operation(String[] parts, Map<String, Integer> intVariables, Map<String, Long> longVariables) {
        String varName = parts[0];
        this.intVariables = intVariables;
        this.longVariables = longVariables;
        for(int i=1; i<parts.length; i++) {
            if(parts[i].equals("샷") && parts[i+1].equals("추가"))
                plusOne(varName);
            if(parts[i].equals("시럽") && parts[i+1].equals("빼고"))
                minusOne(varName);
        }
    }

    public void plusOne(String varName) {
        int temp = 0;
        if(intVariables.containsKey(varName))
            intVariables.replace(varName, intVariables.get(varName) + 1);
        else longVariables.replace(varName, longVariables.get(varName) + 1);
    }

    public void minusOne(String varName) {
        int temp = 0;
        if(intVariables.containsKey(varName))
            intVariables.replace(varName, intVariables.get(varName) - 1);
        else longVariables.replace(varName, longVariables.get(varName) - 1);
    }
}
