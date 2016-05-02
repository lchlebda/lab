package codility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * https://codility.com/programmers/task/tape_equilibrium/
 */
public class TapeEquilibrium {

    private static final Logger logger = LogManager.getLogger(TapeEquilibrium.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", solution(new int[]{-10, -20, -30, -40, 100}));
    }

    private static int solution(int[] A) {
        if (A.length == 2) {
            return Math.abs(A[1] - A[0]);
        }
        int[] sumArr = new int[A.length];
        int sum = 0;
        for (int i=0; i<A.length; i++) {
            sumArr[i] = sum += A[i];
        }
        int min = Integer.MAX_VALUE;
        for (int i=0; i<A.length-1; i++) {
            int diff = Math.abs(2*sumArr[i] - sum);
            if (min > diff) {
                min = diff;
            }
        }

        return min;
    }
}