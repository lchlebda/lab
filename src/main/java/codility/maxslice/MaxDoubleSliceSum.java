package codility.maxslice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * https://codility.com/programmers/task/max_double_slice_sum/
 */
public class MaxDoubleSliceSum {

    private static final Logger logger = LogManager.getLogger(MaxDoubleSliceSum.class);

    public static void main(String[] args) {
//        logger.info("Result: {} ", solution(new int[] {3, 2, 6, -1, 4, 5, -1, 2})); // --> 17
        logger.info("Result: {} ", solution(new int[] {5, -7, 3, 5, -2, 4, -1})); // --> 10
    }

    private static int solution(int[] A) {
        int[] fromLeft = new int[A.length];
        int[] fromRight = new int[A.length];
        int max = 0;
        for (int i = 2; i < A.length; i++) {
            fromLeft[i] = Math.max(0, fromLeft[i-1] + A[i-1]);
        }
        for (int i = A.length - 3; i >= 0; i--) {
            fromRight[i] = Math.max(0, fromRight[i+1] + A[i+1]);
        }
        for (int i = 1; i < A.length - 1; i++) {
            max = Math.max(max, fromLeft[i] + fromRight[i]);        }
        return max;
    }
}