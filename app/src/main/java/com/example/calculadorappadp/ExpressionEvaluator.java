package com.proyecto.calculadorappadp;

public class ExpressionEvaluator {

    public static double evaluate(String expression) {
        // This is a basic evaluator for simple expressions. It doesn't support parentheses or complex operations.
        String[] tokens = expression.split("(?=[-+*/])|(?<=[-+*/])");

        if (tokens.length != 3) {
            return Double.NaN; // Invalid format for this basic calculator
        }

        try {
            double firstOperand = Double.parseDouble(tokens[0]);
            String operator = tokens[1];
            double secondOperand = Double.parseDouble(tokens[2]);

            switch (operator) {
                case "+":
                    return firstOperand + secondOperand;
                case "-":
                    return firstOperand - secondOperand;
                case "*":
                    return firstOperand * secondOperand;
                case "/":
                    if (secondOperand == 0) {
                        return Double.NaN; // Division by zero
                    }
                    return firstOperand / secondOperand;
                default:
                    return Double.NaN; // Unknown operator
            }
        } catch (NumberFormatException e) {
            return Double.NaN; // Error parsing numbers
        }
    }
}
