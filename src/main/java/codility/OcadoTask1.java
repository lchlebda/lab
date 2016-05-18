package codility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 *
 */
public class OcadoTask1 {

    private static final Logger logger = LogManager.getLogger(OcadoTask1.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", solution(new int[]{8, 24, 3, 20, 1, 17})); // --> 2
        logger.info("Result: {} ", solution(new int[]{7, 21, 3, 42, 3, 7})); // --> 0
        logger.info("Result: {} ", solution(new int[]{20, 20, 20})); // --> 0
        logger.info("Result: {} ", solution(new int[]{100000, 0})); // --> 100000
        logger.info("Result: {} ", solution(new int[]{5, 5, 6, 6, 7, 7})); // --> 0
    }

    private static int solution(int[] A) {
        Arrays.sort(A);
        int min = Integer.MAX_VALUE;
        for (int i=1; i<A.length; i++) {
            if (A[i] - A[i-1] < min) {
                min = A[i] - A[i-1];
            }
            if (min == 0) {
                break;
            }
        }

        return min;
    }
}