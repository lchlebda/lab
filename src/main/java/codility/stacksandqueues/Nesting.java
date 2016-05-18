package codility.stacksandqueues;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://codility.com/programmers/task/nesting/
 */
public class Nesting {

    private static final Logger logger = LogManager.getLogger(Nesting.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", solution("(()(())())")); // --> 1
        logger.info("Result: {} ", solution("())")); // --> 0
        logger.info("Result: {} ", solution("((((((")); // --> 0
        logger.info("Result: {} ", solution("(())))")); // --> 0
        logger.info("Result: {} ", solution("()()()()()")); // --> 1
        logger.info("Result: {} ", solution("")); // --> 1
    }

    private static int solution(String S) {
        Deque<Character> stack = new ArrayDeque<>();
        for (Character c : S.toCharArray()) {
            Character last = stack.peek();
            if (last != null && last.equals('(') && c.equals(')')) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        if (stack.size() == 0) {
            return 1;
        } else {
            return 0;
        }
    }
}