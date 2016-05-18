package codility.stacksandqueues;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * https://codility.com/programmers/task/brackets/
 */
public class Brackets {

    private static final Logger logger = LogManager.getLogger(Brackets.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", solution("{[()()]}"));  // --> 1
        logger.info("Result: {} ", solution("([)()]"));  // --> 0
        logger.info("Result: {} ", solution("{}[]()()[]{}"));  // --> 1
        logger.info("Result: {} ", solution("{}[])()([]{}"));  // --> 0
        logger.info("Result: {} ", solution("{"));  // --> 0
        logger.info("Result: {} ", solution(""));  // --> 1
        logger.info("Result: {} ", solution(")(()()()"));  // --> 0
    }

    private static int solution(String S) {
        if (S.isEmpty()) {
            return 1;
        }
        char[] str = S.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('{', '}');
        map.put('[', ']');
        map.put('(', ')');
        for (char c : str) {
            Character lastChar = stack.peek();
            Character keyChar = map.get(lastChar);
            if (lastChar != null && keyChar != null && keyChar.equals(c)) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        if (stack.peek() == null) {
            return 1;
        } else {
            return 0;
        }
    }
}