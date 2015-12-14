package com.getbase.onp;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;


public class ONP {

    public static int calculate(String onp) {
        Deque<Integer> stack = new LinkedList<Integer>();
        String[] onpArr = onp.split(" ");
        for (String str : onpArr) {
            int token;
            try {
                token = Integer.parseInt(str);
                stack.add(token);
            } catch (NumberFormatException exc) {
                int firstNum = stack.removeLast();
                int secondNum = stack.removeLast();
                int result = 0;
                switch (str) {
                    case "+": result = secondNum + firstNum; break;
                    case "-": result = secondNum - firstNum; break;
                    case "*": result = secondNum * firstNum; break;
                    case "/": result = secondNum / firstNum; break;
                }
                stack.add(result);
            }
        }

        return stack.poll();
    }
}
