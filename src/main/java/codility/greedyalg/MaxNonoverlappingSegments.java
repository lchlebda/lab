package codility.greedyalg;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * https://codility.com/programmers/task/max_nonoverlapping_segments/
 */
public class MaxNonoverlappingSegments {

    private static final Logger logger = LogManager.getLogger(MaxNonoverlappingSegments.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", solution(new int[]{1, 3, 7, 9, 9}, new int[]{5, 6, 8, 9, 10})); // --> 3
        logger.info("Result: {} ", solution(new int[]{1, 5}, new int[]{9, 9})); // --> 1
        logger.info("Result: {} ", solution(new int[]{1}, new int[]{9})); // --> 1
        logger.info("Result: {} ", solution(new int[]{}, new int[]{})); // --> 0
    }

    private static int solution(int[] A, int[] B) {
        if (A.length <= 1) {
            return A.length;
        }
        int counter = 1;
        int previousEnd = B[0];
        for (int i=1; i<B.length; i++) {
            if (A[i] > previousEnd) {
                counter++;
                previousEnd = B[i];
            }
        }

        return counter;
    }
}