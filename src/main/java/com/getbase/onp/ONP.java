package com.getbase.onp;

import org.apache.commons.lang3.StringUtils;

import java.util.Deque;
import java.util.LinkedList;

public class ONP {

    public static int calculate(String onp) {

        Deque<Integer> stack = new LinkedList<>();
        String[] onpArr = onp.split(" ");

        for (String token : onpArr) {
            if (StringUtils.isNumeric(token)) {
                stack.add(Integer.parseInt(token));
            } else {
                calculateOperatorAndAddResultToStack(stack, token);
            }
        }

        return stack.poll();
    }

    private static void calculateOperatorAndAddResultToStack(Deque<Integer> stack, String token) {
        Operator operator = FunctionFactory.getInstance(token);
        int[] numberArr = new int[operator.getNumberOfArgs()];
        for (int i = 0; i < numberArr.length; i++) {
            numberArr[i] = stack.removeLast();
        }
        int result = operator.getFunction().evaluate(numberArr);
        stack.add(result);
    }
}
