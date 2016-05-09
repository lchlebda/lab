package codility.sorting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 * https://codility.com/programmers/task/max_product_of_three/
 */
public class MaxProductOfThree {

    private static final Logger logger = LogManager.getLogger(MaxProductOfThree.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", solution(new int[]{-3, 1, 2, -2, 5, 6})); // --> 60
        logger.info("Result: {} ", solution(new int[]{1, -2, 1, -3, 1, 2, 6})); // --> 36
        logger.info("Result: {} ", solution(new int[]{0, -10, 1000})); // --> 0
        logger.info("Result: {} ", solution(new int[]{1000, -10, -10})); // --> 100000
        logger.info("Result: {} ", solution(new int[]{-10, 5, 3, 4, 10})); // --> 200
    }

    private static int solution(int[] A) {
        Arrays.sort(A);
        int size = A.length;
        if (size == 3) {
            return A[0] * A[1] * A[2];
        }
        if (A[1] >= 0) {
            return A[size - 3] * A[size - 2] * A[size - 1];
        } else {
            int firstMult = A[0] * A[1] * A[size-1];
            int secondMult = A[size - 3] * A[size - 2] * A[size - 1];
            return firstMult > secondMult ? firstMult : secondMult;
        }
    }
}