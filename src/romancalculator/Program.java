package romancalculator;

import java.util.Scanner;

/**
 *
 * @author DumitruDaniel
 */
public class Program {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String expression;
        System.out.println("Introdu un numar roman");
        expression = scan.nextLine();
        String result = RomanCalculator.calculate(expression);
        System.out.println(result);
    }
}
