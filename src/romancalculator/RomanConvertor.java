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
    
    private static final HashMap<Integer, String> ARABIC_LIST = new HashMap() {
        {
            put(1, "I");
            put(5, "V");
            put(10,"X");
            put(50,"L");
            put(100,"C");
            put(500,"D");
            put(1000,"M");
            
        }
    };
    
    public static int convertRomanToInt(String romanNumeral) {
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

//            if(getInteger(digit) > getInteger(numeralDigits[0])){
//                System.out.println("Invalid numeral, incorect order of symbols.");
//                return false;
//            }
            
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
    
    public static String convertIntToRoman(int number){
        String numberString = ""+number;
        int contor=numberString.length()-1;
        String result = "";
        for(String s:numberString.split("")){
            String snumber = s+new String(new char[contor]).replace("\0", "0");
            result += toRoman(Integer.parseInt(snumber));
            contor--;
        }
        return result;
    }
    
    private static String toRoman(int number) {
        for(int i=1;i<10;i++){
            if(number% i == 0 && ROMAN_LIST.containsValue(number/i) && i == 9 ){
                return ARABIC_LIST.get((number/i))+ARABIC_LIST.get(number/i*10);
            }else if(number% i == 0 && ROMAN_LIST.containsValue(number/i) && i == 4){
                return ARABIC_LIST.get((number/i))+ARABIC_LIST.get(number/i*5);
            }else if(number% i == 0 && ROMAN_LIST.containsValue(number/i) ){  
                String s = i<4 ? "":ARABIC_LIST.get(number/i*5);
                int m = i <4 ? i:i-5;
                for(int j =1;j<=m;j++){
                    s+=ARABIC_LIST.get((number/i));
                }
                return s;
            }
        }
        return "";
    }
}
