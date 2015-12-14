package com.getbase.onp;


public class Operator {

    private final int numberOfArgs;
    private final MathFunction function;

    public Operator(int numberOfArgs, MathFunction function) {
        this.numberOfArgs = numberOfArgs;
        this.function = function;
    }

    public int getNumberOfArgs() {
        return numberOfArgs;
    }

    public MathFunction getFunction() {
        return function;
    }
}
