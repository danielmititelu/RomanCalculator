package romancalculator;
import java.util.Scanner;

/**
 *
 * @author DumitruDaniel
 */
public class RomanCalculator {
    public static void main(String[] args) {
        int numeral;
        Scanner scan= new Scanner(System.in);
        String romanNumeral;
        System.out.println("Introdu un numar roman");
        romanNumeral = scan.next();
        numeral = RomanConvertor.RomanToInt(romanNumeral);
        System.out.println("Numarul introdus este:"+numeral);
    }
}
