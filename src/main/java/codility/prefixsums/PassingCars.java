package codility.prefixsums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * https://codility.com/programmers/task/passing_cars/
 */
public class PassingCars {

    private static final Logger logger = LogManager.getLogger(PassingCars.class);

    public static void main(String[] args) {
//        int[] A = new int[]{0, 1, 0, 1, 1};  // --> 5
//        int[] A = new int[]{1, 1, 1};  // --> 0
//        int[] A = new int[]{1, 1, 0};  // --> 0
//        int[] A = new int[]{0, 0, 0, 1};  // --> 3
        int[] A = new int[]{0, 0, 1, 0, 1, 0, 0, 1};  // --> 10
        logger.info("Result: {} ", solution(A));
    }

    private static int solution(int[] A) {
        long sum = 0;
        int zeroCounter = 0;
        for (int i : A) {
            if (i == 0) {
                zeroCounter++;
            } else {
                sum += zeroCounter;
            }
        }

        if (sum > Math.pow(10, 9)) {
            return -1;
        } else {
            return (int) sum;
        }
    }
}