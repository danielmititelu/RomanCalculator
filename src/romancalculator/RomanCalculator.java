package romancalculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author DumitruDaniel
 */
public class RomanCalculator {
    static String DELIMITERS = "(+-*/^)";
        
    public static String calculate(String expression) { 
        if(expression.chars().filter(chr -> chr == '(').count()  != expression.chars().filter(chr -> chr == ')').count() ){
            return "You are missing some parentheses";
        }
        expression = "(" + expression + ")";
        expression = expression.replaceAll(" ", "");
        Pattern p = Pattern.compile("((?<=\\()[^\\(\\)]+?(?=\\)))");
        Matcher m = p.matcher(expression);
        while (m.find()) {
            String convertedExpression = convertExpressionToInt(m.group());
            String expressionResult = calculateExpression(convertedExpression);
            if(Integer.parseInt(expressionResult) < 0){ System.out.println("Negative values cannot be represented in roman numerals"); break;}
            String convertedResult = NumeralConverter.convertIntToRoman(Integer.parseInt(expressionResult));
            expression = expression.replaceFirst(Pattern.quote("(" + m.group() + ")"), Matcher.quoteReplacement(convertedResult));
            m = p.matcher(expression);
            System.out.println(expression);
        }
        return expression;
    }

    private static int getLimit(String expression, int opIndex, boolean right) {
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

    private static String calculateExpression(String expression) { 
        Pattern p = Pattern.compile("\\^");
        Matcher m = p.matcher(expression);
        while (m.find()) {
            String gradeIIIOperation = expression.substring(getLimit(expression, m.start(), false), getLimit(expression, m.start(), true));
            String opResult = calculateOperation(gradeIIIOperation);
            expression = expression.substring(0, getLimit(expression, m.start(), false)) + opResult + expression.substring(getLimit(expression, m.start(), true));
            m = p.matcher(expression);
        }
        
        p = Pattern.compile("\\*|\\/");
        m = p.matcher(expression);
        while (m.find()) {
            String gradeIIOperation = expression.substring(getLimit(expression, m.start(), false), getLimit(expression, m.start(), true));
            String opResult = calculateOperation(gradeIIOperation);
            if(Integer.parseInt(opResult) < 0){ return opResult;}
            expression = expression.substring(0, getLimit(expression, m.start(), false)) + opResult + expression.substring(getLimit(expression, m.start(), true));
            m = p.matcher(expression);
        }

        p = Pattern.compile("\\+|\\-");
        m = p.matcher(expression);
        while (m.find()) {
            String gradeIOperation = expression.substring(getLimit(expression, m.start(), false), getLimit(expression, m.start(), true));
            String opResult = calculateOperation(gradeIOperation);
            if(Integer.parseInt(opResult) < 0){ return opResult;}
            expression = expression.substring(0, getLimit(expression, m.start(), false)) + opResult + expression.substring(getLimit(expression, m.start(), true));
            m = p.matcher(expression);
        }
        return expression;
    }

    private static String calculateOperation(String expression) {
        if (expression.contains("*")) {
            String[] numbers = expression.split("\\*");
            return "" + (Integer.parseInt(numbers[0]) * Integer.parseInt(numbers[1]));
        } else if(expression.contains("/")) {
            String[] numbers = expression.split("\\/");
            return "" + (Integer.parseInt(numbers[0]) / Integer.parseInt(numbers[1]));
        }else if (expression.contains("+")) {
            String[] numbers = expression.split("\\+");
            return "" + (Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[1]));
        } else if (expression.contains("-")){
            String[] numbers = expression.split("\\-");
            return "" + (Integer.parseInt(numbers[0]) - Integer.parseInt(numbers[1]));
        } else if (expression.contains("^")){
            String[] numbers = expression.split("\\^");
            return "" + (int)Math.pow(Integer.parseInt(numbers[0]) , Integer.parseInt(numbers[1]));
        }else {
            System.out.println("No operator found in expression: " + expression);
            return "";
        }
    }

    private static String convertExpressionToInt(String expression) {
        for (String s : expression.split("\\+|\\*|-|\\/|\\^")) {
            expression = expression.replaceFirst(s.trim(), "" + NumeralConverter.convertRomanToInt(s.trim()));
        }
        return expression;
    }
}
