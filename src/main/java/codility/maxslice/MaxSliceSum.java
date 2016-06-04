package codility.maxslice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * https://codility.com/programmers/task/max_slice_sum/
 */
public class MaxSliceSum {

    private static final Logger logger = LogManager.getLogger(MaxSliceSum.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", solution(new int[]{3, 2, -6, 4, 0})); // --> 5
        logger.info("Result: {} ", solution(new int[]{-10})); // --> -10
        logger.info("Result: {} ", solution(new int[]{Integer.MIN_VALUE})); // --> minvalue
        logger.info("Result: {} ", solution(new int[]{Integer.MAX_VALUE})); // --> maxvalue
        logger.info("Result: {} ", solution(new int[]{1, 2, 3, 4, 100, 200})); // --> 310
        logger.info("Result: {} ", solution(new int[]{1, -2})); // --> 1
        logger.info("Result: {} ", solution(new int[]{-2, 1})); // --> 1
    }

    private static int solution(int[] A) {
        int maxSlice = 0;
        int maxEnding = 0;
        int maxLessThanZero = Integer.MIN_VALUE;
        for (int a : A) {
            if (a <= 0 && a > maxLessThanZero) {
                maxLessThanZero = a;
            }
            maxEnding = Math.max(0, maxEnding + a);
            maxSlice = Math.max(maxSlice, maxEnding);
        }
        if (maxSlice == 0) {
            return maxLessThanZero;
        } else {
            return maxSlice;
        }
    }
}