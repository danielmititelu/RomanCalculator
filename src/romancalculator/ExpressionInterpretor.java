package romancalculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author DumitruDaniel
 */
public class ExpressionInterpretor {

    public static String splitExpression(String expression) {
        expression = expression.replaceAll(" ", "");
        Pattern p = Pattern.compile("((?<=\\()[^\\(\\)]+?(?=\\)))");
        Matcher m = p.matcher(expression);
        while (m.find()) {
            String convertedExpression = convertExpression(m.group());
            String expressionResult = calculateExpression(convertedExpression);
            String convertedResult = RomanConvertor.convertIntToRoman(Integer.parseInt(expressionResult));
            expression = expression.replaceFirst(Pattern.quote("(" + m.group() + ")"), Matcher.quoteReplacement(convertedResult));
            m = p.matcher(expression);
            System.out.println(expression);
        }
        return expression;
    }

    private static int take(String expression, int opIndex, boolean right) {
        String DELIMITERS = "(+-*/)";

        opIndex = right ? opIndex + 1 : opIndex - 1;
        while (true) {
            if (opIndex > expression.length() - 1 || opIndex < 0) {
                return right ? expression.length() : 0;
            }
            if (DELIMITERS.contains("" + expression.charAt(opIndex))) {
                return right ? opIndex : opIndex + 1;
            }
            opIndex = right ? opIndex + 1 : opIndex - 1;
        }
    }

    private static String calculateExpression(String expression) {//10+70*500
        Pattern p = Pattern.compile("\\*|\\/");
        Matcher m = p.matcher(expression);
        while (m.find()) {
            String gradeIIOperation = expression.substring(take(expression, m.start(), false), take(expression, m.start(), true));
            String opResult = calculateGradeIIOperation(gradeIIOperation);
            expression = expression.substring(0, take(expression, m.start(), false)) + opResult + expression.substring(take(expression, m.start(), true));
            m = p.matcher(expression);
        }

        p = Pattern.compile("\\+|\\-");
        m = p.matcher(expression);
        while (m.find()) {
            String gradeIOperation = expression.substring(take(expression, m.start(), false), take(expression, m.start(), true));
            String opResult = calculateGradeIOperation(gradeIOperation);
            expression = expression.substring(0, take(expression, m.start(), false)) + opResult + expression.substring(take(expression, m.start(), true));
            m = p.matcher(expression);
        }
        return expression;
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

    private static String convertExpression(String expression) {
        for (String s : expression.split("\\+|\\*|-|\\/")) {
            expression = expression.replaceFirst(s.trim(), "" + RomanConvertor.convertRomanToInt(s.trim()));
        }
        return expression;
    }
}
