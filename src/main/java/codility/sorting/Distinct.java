package codility.sorting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 * https://codility.com/programmers/task/distinct/
 */
public class Distinct {

    private static final Logger logger = LogManager.getLogger(Distinct.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", solution(new int[]{2, 1, 1, 2, 3, 1})); // --> 3
        logger.info("Result: {} ", solution(new int[]{-10, -10, -10, -10})); // --> 1
    }

    private static int solution(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        Arrays.sort(A);
        int counter = 1;
        for (int i=1; i<A.length; i++) {
            if (A[i-1] < A[i]) {
                counter++;
            }
        }

        return counter;
    }
}