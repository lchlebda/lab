package codility.sorting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 * https://codility.com/programmers/task/triangle/
 */
public class Triangle {

    private static final Logger logger = LogManager.getLogger(Triangle.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", solution(new int[]{10, 2, 5, 1, 8, 20})); // --> 1
        logger.info("Result: {} ", solution(new int[]{10, 50, 5, 1})); // --> 0
        logger.info("Result: {} ", solution(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE})); // --> 1
    }

    private static int solution(int[] A) {
        Arrays.sort(A);
        for (int i=2; i<A.length; i++) {
            if (((long)A[i-2] + A[i-1]) <= A[i]) {
                continue;
            } else {
                return 1;
            }
        }

        return 0;
    }
}