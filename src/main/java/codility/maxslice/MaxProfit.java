package codility.maxslice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *  https://codility.com/programmers/task/max_profit/
 */
public class MaxProfit {

    private static final Logger logger = LogManager.getLogger(MaxProfit.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", solution(new int[]{23171, 21011, 21123, 21366, 21013, 21367})); // --> 356
        logger.info("Result: {} ", solution(new int[]{5, 4, 6, 10, 11, 0})); // --> 7
        logger.info("Result: {} ", solution(new int[]{})); // --> 0

        logger.info("Result: {} ", solution(new int[]{10})); // --> 0
        logger.info("Result: {} ", solution(new int[]{10, 9})); // --> 0
        logger.info("Result: {} ", solution(new int[]{5, 10})); // --> 5
        logger.info("Result: {} ", solution(new int[]{8, 9, 3, 6, 1, 2})); // --> 3
    }

    private static int solution(int[] A) {
        int min = 200000;
        int max = 0;
        int difference = 0;
        for (int a : A) {
            if (a > max) {
                max = a;
                if (max - min > difference) {
                    difference = max - min;
                }
            }
            if (a < min) {
                min = a;
                max = 0;
            }
        }

        return difference > 0 ? difference : 0;
    }
}