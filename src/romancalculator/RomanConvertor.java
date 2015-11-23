package romancalculator;

import java.util.HashMap;

/**
 *
 * @author DumitruDaniel
 */
public class RomanConvertor {

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

    public static int convertRomanNumeral(String romanNumeral) {
        String upperCaseRomanNumeral = romanNumeral.toUpperCase();
        if (!isValidRomanNumeral(upperCaseRomanNumeral)) {
            return 0;
        }
        return getArabicNumber(upperCaseRomanNumeral);
    }

    private static int getInteger(String key) {
        return ROMAN_LIST.get(key);
    }

    private static boolean isValidRomanNumeral(String romanNumeral) {
        String[] numeralDigits = romanNumeral.split("");
        int sameDigitCount = 1;
        String oldDigit = "";
        for (String digit : numeralDigits) {
            if (!ROMAN_LIST.containsKey(digit)) {
                System.out.println("Invalid numeral, unknown '" + digit + "' symbol.");
                return false;
            }

            if(getInteger(digit) > getInteger(numeralDigits[0]) && getInteger(numeralDigits[0])%5 == 0 ){
                System.out.println("Invalid numeral, incorect order of symbols.");
                return false;
            }
            
            if (sameDigitCount >= 2 && getInteger(oldDigit) < getInteger(digit)) {
                System.out.println("Invalid numeral, contains more than 2 '" + oldDigit + "' before '" + digit + "' symbols.");
                return false;
            }

            sameDigitCount = oldDigit.equals(digit) ? sameDigitCount + 1 : 1;
            if (sameDigitCount == 4) {
                System.out.println("Invalid numeral, contains more than 3 '" + digit + "' symbols.");
                return false;
            }
            oldDigit = digit;
        }
        return true;
    }

    private static int getArabicNumber(String romanNumeral) {
        int result = 0;
        int current = 0;
        int next = 0;

        String[] elements = romanNumeral.split("");
        for (int i = 0; i < elements.length; i++) {
            current = getInteger(elements[i]);
            if (i + 1 < elements.length) {
                next = getInteger(elements[i + 1]);
            }

            if (current >= next) {
                result += current;
            } else {
                result -= current;
            }
        }
        return result;
    }
}
