package codility.prefixsums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * https://codility.com/programmers/task/min_avg_two_slice/
 */
public class MinAvgTwoSlice {

    private static final Logger logger = LogManager.getLogger(MinAvgTwoSlice.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", solution(new int[]{4,2,2,5,1,5,8})); // --> 1
        logger.info("Result: {} ", solution(new int[]{-3, -5, -8, -4, -10})); // --> 2
        logger.info("Result: {} ", solution(new int[]{-10, -10})); // --> 0
        logger.info("Result: {} ", solution(new int[]{-10, -10, -10, -10})); // --> 0
    }

    private static int solution(int[] A) {
        int iMin = 0;
        double avgMin = (double)(A[0] + A[1])/2;

        for (int i=2; i<A.length; i++) {
            double avgCurrTwo = (double)(A[i-1] + A[i])/2;
            double avgCurrThree = (double)(A[i-2] + A[i-1] + A[i])/3;
            if (avgCurrTwo < avgMin) {
                iMin = i-1;
                avgMin = avgCurrTwo;
            }
            if (avgCurrThree < avgMin) {
                iMin = i-2;
                avgMin = avgCurrThree;
            }
        }

        return iMin;
    }
}