package romancalculator;

/**
 *
 * @author DumitruDaniel
 */
public class NumeralConverter {

    public static int convertRomanToInt(String romanNumeral) {
        String upperCaseRomanNumeral = romanNumeral.toUpperCase();
        if (!isValidRomanNumeral(upperCaseRomanNumeral)) {
            return 0;
        }
        return getArabicNumber(upperCaseRomanNumeral);
    }

    private static boolean isValidRomanNumeral(String romanNumeral) {
        if (romanNumeral.matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$")) {
            return true;
        }else{
            System.out.println(romanNumeral + ":Invalid roman numeral.");
            return false;
        }
    }

    private static int getArabicNumber(String romanNumeral) {
        int result = 0;
        int current = 0;
        int next = 0;

        String[] elements = romanNumeral.split("");
        for (int i = 0; i < elements.length; i++) {
            current = Numerals.getInteger(elements[i]);
            if (i + 1 < elements.length) {
                next = Numerals.getInteger(elements[i + 1]);
            }

            if (current >= next) {
                result += current;
            } else {
                result -= current;
            }
        }
        return result;
    }

    public static String convertIntToRoman(int number) {
        String numberString = "" + number;
        int contor = numberString.length() - 1;
        String result = "";
        for (String s : numberString.split("")) {
            String snumber = s + new String(new char[contor]).replace("\0", "0");
            result += toRoman(Integer.parseInt(snumber));
            contor--;
        }
        return result;
    }

    private static String toRoman(int number) {
        for (int i = 1; i < 10; i++) {
            if (number % i == 0 && Numerals.contains(number / i) && i == 9) {
                return Numerals.getRoman((number / i)) + Numerals.getRoman(number / i * 10);
            } else if (number % i == 0 && Numerals.contains(number / i) && i == 4) {
                return Numerals.getRoman((number / i)) + Numerals.getRoman(number / i * 5);
            } else if (number % i == 0 && Numerals.contains(number / i)) {
                String s = i < 4 ? "" : Numerals.getRoman(number / i * 5);
                int m = i < 4 ? i : i - 5;
                for (int j = 1; j <= m; j++) {
                    s += Numerals.getRoman((number / i));
                }
                return s;
            }
        }
        return "";
    }
}
