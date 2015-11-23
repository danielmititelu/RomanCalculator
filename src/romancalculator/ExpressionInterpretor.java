package romancalculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static romancalculator.RomanConvertor.convertRomanNumeral;

/**
 *
 * @author DumitruDaniel
 */
public class ExpressionInterpreator {

    public static void splitExpression(String expression) {// ((X + VII * V) *III + ((I + II )+ III))
        expression = expression.replaceAll(" ", "");
        Pattern p = Pattern.compile("((?<=\\()[^\\(].+?(?=\\)))");
        Matcher m = p.matcher(expression);
        while (m.find()) {
            String cinverterExpression = convertExpression(m.group());
            calculateExpression(cinverterExpression);
        }
    }

    private static int take(String expression, int opIndex, boolean right) {
        String DELIMITERS = "(+-*/)";

        opIndex = right ? opIndex + 1 : opIndex - 1;
        while (true) {
            if (opIndex > expression.length() - 1 || opIndex < 0) {
                return right ? expression.length() : 0;
            }
            if (DELIMITERS.contains("" + expression.charAt(opIndex))) {
                return right ? opIndex - 1 : opIndex + 1;
            }
            opIndex = right ? opIndex + 1 : opIndex - 1;
        }
    }

    private static void calculateExpression(String expression) {//10+70*500
        Pattern p = Pattern.compile("\\*|\\/");
        Matcher m = p.matcher(expression);
        while (m.find()) {
            String gradeIIOperation = expression.substring(take(expression, m.start(), false), take(expression, m.start(), true));
            String opResult = calculateGradeIIOperation(gradeIIOperation);
            expression = expression.replace(gradeIIOperation, opResult);
        }
        System.out.println(expression);

        p = Pattern.compile("\\+|\\-");
        m = p.matcher(expression);
        while (m.find()) {
            String gradeIOperation = expression.substring(take(expression, m.start(), false), take(expression, m.start(), true));
            String opResult = calculateGradeIOperation(gradeIOperation);
            expression = expression.replace(gradeIOperation, opResult);
        }
        System.out.println(expression);
    }

    private static String calculateGradeIIOperation(String operation) {// 7*5
        int result = 0;
        if (operation.contains("*")) {
            String[] numbers = operation.split("\\*");
            result = Integer.parseInt(numbers[0]) * Integer.parseInt(numbers[1]);
        } else {
            String[] numbers = operation.split("\\/");
            result = Integer.parseInt(numbers[0]) / Integer.parseInt(numbers[1]);
        }
        return "" + result;
    }

    private static String calculateGradeIOperation(String operation) {
        int result = 0;
        if (operation.contains("+")) {
            String[] numbers = operation.split("\\+");
            result = Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[1]);
        } else {
            String[] numbers = operation.split("\\-");
            result = Integer.parseInt(numbers[0]) - Integer.parseInt(numbers[1]);
        }
        return "" + result;
    }

    private static String convertExpression(String expression) { //X + VII * V
        for (String s : expression.split("\\+|\\*|-|\\/")) {
            expression = expression.replace(s.trim(), "" + convertRomanNumeral(s.trim()));
        }
        return expression;
    }
}
