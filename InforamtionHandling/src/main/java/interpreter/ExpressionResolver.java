package interpreter;


import exception.InterpreterException;
import interpreter.util.OperatorConstant;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ExpressionResolver {

    private static final String SPACE_REGEX = " ";
    private static final String BLANK_REGEX = "\\s+";
    private static final String INCREMENT_DECREMENT_REGEX = "([+-]{2}\\d+|\\d+[+-]{2})";
    private static final String NUMBER_REGEX = "\\d";
    private static final String INC_DEC_OPERATOR_REGEX = "[+-]{2}";
    private static final String EXPRESSION_PART_REGEX = "\\d+|[+-/*()]";
    private static final String UNARY_PREFIX_OPERATION_REGEX = "^[+-](?=\\d+)|(?<=\\()[+-](?=\\d+)";
    private static final String UNARY_POSTFIX_OPERATION_REGEX = "(?<=\\d)[+-]\\Z|(?<=\\d)[+-](?=\\))";

    private static final Map<String, Integer> OPERATORS = new HashMap<String, Integer>() {
        {
            put(OperatorConstant.ADD, 0);
            put(OperatorConstant.SUB, 0);
            put(OperatorConstant.MUL, 5);
            put(OperatorConstant.DIV, 5);
        }
    };

    public static List<String> convertToPolishNotation(String expression) {
        expression = addSpaces(expression);
        String[] expr = expression.split(SPACE_REGEX);
        String[] normalizedExpr = convertInfixToReversePolishNotation(expr);
        return Arrays.asList(normalizedExpr);
    }

    private static String[] convertInfixToReversePolishNotation(String[] inputTokens) {
        ArrayList<String> converted = new ArrayList<>();
        ArrayDeque<String> stack = new ArrayDeque<>();
        for (String token : inputTokens) {
            if (isOperator(token)) {
                while (!stack.isEmpty() && isOperator(stack.peek())) {
                    if (priority(token, stack.peek()) <= 0)  {
                        converted.add(stack.pop());
                        continue;
                    }
                    break;
                }
                stack.push(token);
            } else if (OperatorConstant.LEFT_BRACKET.equals(token)) {
                stack.push(token);
            } else if (OperatorConstant.RIGHT_BRACKET.equals(token)) {
                while (!stack.isEmpty() && !OperatorConstant.LEFT_BRACKET.equals(stack.peek())) {
                    converted.add(stack.pop());
                }
                stack.pop();
            } else {
                converted.add(token);
            }
        }
        while (!stack.isEmpty()) {
            converted.add(stack.pop());
        }
        String[] result = new String[converted.size()];
        return converted.toArray(result);
    }

    public static String resolveOperators(String expression) throws InterpreterException {
        String subExpr;
        String val = "";
        String number;
        String operator;
        Matcher incDecExprMatcher = Pattern.compile(INCREMENT_DECREMENT_REGEX).matcher(expression);
        try {
            while (incDecExprMatcher.find()) {
                subExpr = incDecExprMatcher.group();
                operator = extractOperator(subExpr);
                number = extractNumber(subExpr);
                val = evaluateValue(number, operator);
            }
        } catch (IllegalStateException e) {
            throw new InterpreterException(e);
        }
        expression = expression.replaceFirst(INCREMENT_DECREMENT_REGEX, val);
        return ExpressionResolver.convertUnaryToBinary(expression);
    }

    private static String extractNumber(String expression) throws IllegalStateException {
        Matcher numberMatcher = Pattern.compile(NUMBER_REGEX).matcher(expression);
        numberMatcher.find();
        return numberMatcher.group();
    }

    private static String extractOperator(String expression) throws IllegalStateException {
        Matcher operatorMatcher = Pattern.compile(INC_DEC_OPERATOR_REGEX).matcher(expression);
        operatorMatcher.find();
        return operatorMatcher.group();
    }

    private static String evaluateValue(String numberString, String operator) throws NumberFormatException, InterpreterException {
        int value = Integer.parseInt(numberString);
        if (operator.equals(OperatorConstant.INC)) {
            return String.valueOf(++value);
        } else if (operator.equals(OperatorConstant.DEC)) {
            return String.valueOf(--value);

        } else {
            throw new InterpreterException();
        }
    }

    private static boolean isOperator(String part) {
        return OPERATORS.containsKey(part);
    }

    private static int priority(String token1, String token2) {
        return OPERATORS.get(token1) - OPERATORS.get(token2);
    }

    private static String addSpaces(String s) {
        Matcher partMatcher = Pattern.compile(EXPRESSION_PART_REGEX).matcher(s);
        String part;
        while (partMatcher.find()) {
            part = partMatcher.group();
            s = s.replace(part, part + " ");
        }
        return s.replaceAll(BLANK_REGEX, " ").trim();
    }

    private static String convertUnaryToBinary(String expression) {
        Matcher prefix = Pattern.compile(UNARY_PREFIX_OPERATION_REGEX).matcher(expression);
        Matcher postfix = Pattern.compile(UNARY_POSTFIX_OPERATION_REGEX).matcher(expression);
        String part;
        if (prefix.find()) {
            part = prefix.group();
            expression = expression.replaceAll(UNARY_PREFIX_OPERATION_REGEX, OperatorConstant.ZERO + part);
        } else if (postfix.find()) {
            part = postfix.group();
            expression = expression.replaceAll(UNARY_POSTFIX_OPERATION_REGEX, part + OperatorConstant.ZERO);
        }
        return expression;
    }

}

