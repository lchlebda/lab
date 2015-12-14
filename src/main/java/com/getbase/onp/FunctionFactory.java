package com.getbase.onp;

import java.util.HashMap;
import java.util.Map;

public class FunctionFactory {

    private static final Map<String, Operator> functions = new HashMap<>();

    static {
        functions.put("+", new Operator(2, (num) -> num[1] + num[0]));
        functions.put("-", new Operator(2, (num) -> num[1] - num[0]));
        functions.put("*", new Operator(2, (num) -> num[1] * num[0]));
        functions.put("/", new Operator(2, (num) -> num[1] / num[0]));
        functions.put("quadfun", new Operator(3, (num) -> num[2]*num[2] + num[1] - num[0]));

    }

    public static Operator getInstance(String operator) {
        return functions.get(operator);
    }
}
