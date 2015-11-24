package romancalculator;

import java.util.Scanner;
import static romancalculator.ExpressionInterpretor.splitExpression;

/**
 *
 * @author DumitruDaniel
 */
public class RomanCalculator {

    public static void main(String[] args) {
        int numeral;
        Scanner scan = new Scanner(System.in);
        String expression;
        System.out.println("Introdu un numar roman");
        expression = scan.nextLine();
        //numeral = RomanConvertor.convertRomanNumeral(romanNumeral);
        //System.out.println("Numarul introdus este:"+numeral);

        //System.out.println("Numarul introdus este:"+getRomanNumeral(Integer.parseInt(romanNumeral)));
        String result = RomanCalculator.calculate(expression);
        System.out.println(result);
    }

    private static String calculate(String expression) {
        String result = splitExpression("(" + expression + ")");
        return result;
    }
}
