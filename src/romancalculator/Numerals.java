package romancalculator;

import java.util.HashMap;

/**
 *
 * @author DumitruDaniel
 */
public class Numerals {
        private static final HashMap<String, Integer> ROMAN_LIST = new HashMap() {
        {
            put("I", 1);
            put("V", 5);
            put("X", 10);
            put("L", 50);
            put("C", 100);
            put("D", 500);
            put("M", 1000);
        }
    };

    private static final HashMap<Integer, String> INTEGER_LIST = new HashMap() {
        {
            put(1, "I");
            put(5, "V");
            put(10, "X");
            put(50, "L");
            put(100, "C");
            put(500, "D");
            put(1000, "M");

        }
    };
    
    public static int getInteger(String key) {
        return ROMAN_LIST.get(key);
    }
    
    public static String getRoman(int key) {
        return INTEGER_LIST.get(key);
    }
    
    public static boolean contains(String key) {
        return ROMAN_LIST.containsKey(key);
    }
    
    public static boolean contains(int key) {
        return INTEGER_LIST.containsKey(key);
    }
}
