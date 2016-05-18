package codility.stacksandqueues;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://codility.com/programmers/task/fish/
 */
public class FishProblem {

    private static final Logger logger = LogManager.getLogger(Fish.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", solution(new int[]{4, 3, 2, 1, 5}, new int[]{0, 1, 0, 0, 0})); // --> 2
        logger.info("Result: {} ", solution(new int[]{4, 3, 2, 1, 5}, new int[]{0, 1, 1, 1, 0})); // --> 2
        logger.info("Result: {} ", solution(new int[]{4, 3, 2, 1, 5}, new int[]{0, 1, 1, 1, 1})); // --> 5
        logger.info("Result: {} ", solution(new int[]{4}, new int[]{0})); // --> 1
        logger.info("Result: {} ", solution(new int[]{4}, new int[]{1})); // --> 1
        logger.info("Result: {} ", solution(new int[]{4, 3}, new int[]{0, 1})); // --> 2
        logger.info("Result: {} ", solution(new int[]{4, 3}, new int[]{1, 0})); // --> 1
        logger.info("Result: {} ", solution(new int[]{4, 3}, new int[]{1, 1})); // --> 2
        logger.info("Result: {} ", solution(new int[]{4, 3}, new int[]{0, 0})); // --> 2
        logger.info("Result: {} ", solution(new int[]{3, 4}, new int[]{1, 0})); // --> 1
    }

    private static int solution(int[] A, int[] B) {
        Deque<Fish> stack = new ArrayDeque<>();
        stack.push(new Fish(A[0], B[0]));
        for (int i=1; i<A.length; i++) {
            Fish fish = new Fish(A[i], B[i]);
            boolean bool = true;
            while (stack.peek() != null && stack.peek().direction == 1 && fish.direction == 0) {
                if (stack.peek().size < fish.size) {
                    stack.pop();
                } else {
                    bool = false;
                    break;
                }
            }
            if (bool) {
                stack.push(fish);
            }
        }
        return stack.size();
    }

    static class Fish {

        public final int size;
        public final int direction;

        public Fish(int size, int direction) {
            this.size = size;
            this.direction = direction;
        }
    }
}