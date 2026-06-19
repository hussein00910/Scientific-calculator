package com.example.calculator;

public interface ButtonCallback {
    void onDigit(String digit);
    void onDot();
    void onOperator(String op);
    void onEqual();
    void onClear();
    void onSign();
    void onPercent();
    void onScientific(String func);
    void onConstant(double value);
}
