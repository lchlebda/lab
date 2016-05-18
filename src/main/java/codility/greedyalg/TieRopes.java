package codility.greedyalg;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * https://codility.com/programmers/task/tie_ropes/
 */
public class TieRopes {

    private static final Logger logger = LogManager.getLogger(TieRopes.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", solution(4, new int[]{1, 2, 3, 4, 1, 1, 3})); // --> 3
        logger.info("Result: {} ", solution(2, new int[]{1, 2, 3, 4, 1, 1, 3})); // --> 5
        logger.info("Result: {} ", solution(3, new int[]{3, 3, 3, 3})); // -->  4
        logger.info("Result: {} ", solution(3, new int[]{10})); // -->  1
        logger.info("Result: {} ", solution(30, new int[]{10})); // -->  0
    }

    private static int solution(int K, int[] A) {
        long length = 0;
        int counter = 0;
        for (int a : A) {
            length += a;
            if (length >= K) {
                length = 0;
                counter++;
            }
        }

        return counter;
    }
}