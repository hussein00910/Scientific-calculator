package com.example.calculator;

public class CalculatorEngine {

    private double operand1 = 0;
    private double operand2 = 0;
    private String operator = "";
    private boolean newInput = true;
    private String currentInput = "0";
    private String expression = "";
    private boolean justEvaluated = false;

    public String getCurrentInput() { return currentInput; }
    public String getExpression()   { return expression; }

    public void inputDigit(String digit) {
        if (justEvaluated) {
            currentInput = digit;
            expression = "";
            justEvaluated = false;
        } else if (newInput) {
            currentInput = digit;
            newInput = false;
        } else {
            if (currentInput.equals("0") && !digit.equals(".")) {
                currentInput = digit;
            } else {
                currentInput += digit;
            }
        }
    }

    public void inputDot() {
        if (justEvaluated) {
            currentInput = "0.";
            expression = "";
            justEvaluated = false;
            newInput = false;
            return;
        }
        if (newInput) {
            currentInput = "0.";
            newInput = false;
        } else if (!currentInput.contains(".")) {
            currentInput += ".";
        }
    }

    public void setOperator(String op) {
        justEvaluated = false;
        if (!operator.isEmpty() && !newInput) {
            evaluate();
            expression = formatNum(operand1) + " " + op;
        } else {
            operand1 = parseNum(currentInput);
            expression = formatNum(operand1) + " " + op;
        }
        operator = op;
        newInput = true;
    }

    public String evaluate() {
        if (operator.isEmpty()) return currentInput;

        operand2 = parseNum(currentInput);
        double result;

        switch (operator) {
            case "+": result = operand1 + operand2; break;
            case "−": result = operand1 - operand2; break;
            case "×": result = operand1 * operand2; break;
            case "÷":
                if (operand2 == 0) { reset(); return "Error"; }
                result = operand1 / operand2;
                break;
            case "xʸ": result = Math.pow(operand1, operand2); break;
            default:   result = operand2;
        }

        expression = formatNum(operand1) + " " + operator + " " + formatNum(operand2) + " =";
        operand1 = result;
        currentInput = formatNum(result);
        operator = "";
        newInput = true;
        justEvaluated = true;
        return currentInput;
    }

    public void applyScientific(String func) {
        double val = parseNum(currentInput);
        double result;
        String label = func + "(" + formatNum(val) + ")";

        switch (func) {
            case "sin":  result = Math.sin(Math.toRadians(val)); break;
            case "cos":  result = Math.cos(Math.toRadians(val)); break;
            case "tan":
                if (val % 180 == 90) { currentInput = "Error"; expression = label + " = Error"; return; }
                result = Math.tan(Math.toRadians(val));
                break;
            case "log":
                if (val <= 0) { currentInput = "Error"; expression = label + " = Error"; return; }
                result = Math.log10(val);
                break;
            case "ln":
                if (val <= 0) { currentInput = "Error"; expression = label + " = Error"; return; }
                result = Math.log(val);
                break;
            case "√":
                if (val < 0) { currentInput = "Error"; expression = label + " = Error"; return; }
                result = Math.sqrt(val);
                break;
            case "n!":
                if (val < 0 || val != Math.floor(val) || val > 20) {
                    currentInput = "Error"; expression = label + " = Error"; return;
                }
                result = factorial((int) val);
                break;
            case "1/x":
                if (val == 0) { currentInput = "Error"; expression = label + " = Error"; return; }
                result = 1.0 / val;
                break;
            case "eˣ": result = Math.exp(val); break;
            default:    return;
        }

        expression = label + " =";
        currentInput = formatNum(result);
        operand1 = result;
        newInput = true;
        justEvaluated = true;
    }

    public void toggleSign() {
        double val = parseNum(currentInput);
        currentInput = formatNum(-val);
    }

    public void percent() {
        double val = parseNum(currentInput);
        currentInput = formatNum(val / 100.0);
    }

    public void setConstant(double value) {
        currentInput = formatNum(value);
        newInput = false;
        justEvaluated = false;
    }

    public void backspace() {
        if (currentInput.length() > 1) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
            if (currentInput.equals("-")) currentInput = "0";
        } else {
            currentInput = "0";
        }
        justEvaluated = false;
    }

    public void reset() {
        operand1 = 0;
        operand2 = 0;
        operator = "";
        currentInput = "0";
        expression = "";
        newInput = true;
        justEvaluated = false;
    }

    private double parseNum(String s) {
        try { return Double.parseDouble(s); } catch (NumberFormatException e) { return 0; }
    }

    static String formatNum(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) return "Error";
        if (d == Math.floor(d) && !Double.isInfinite(d) && Math.abs(d) < 1e15) {
            return String.valueOf((long) d);
        }
        String s = String.format("%.10f", d).replaceAll("0+$", "").replaceAll("\\.$", "");
        return s;
    }

    private long factorial(int n) {
        long r = 1;
        for (int i = 2; i <= n; i++) r *= i;
        return r;
    }
}
