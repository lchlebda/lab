package codility.countingelements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * https://codility.com/programmers/task/frog_river_one/
 */
public class FrogRiverOne {

    private static final Logger logger = LogManager.getLogger(FrogRiverOne.class);

    public static void main(String[] args) {
//        int[] A = new int[] {1, 3, 1, 4, 2, 3, 5, 4}; // --> 6
//        int[] A = new int[] {1, 3, 1, 4, 5, 3, 5, 4};  // --> -1
//        int[] A = new int[] {1, 1, 1, 1, 1, 1, 5, 5};  // --> -1
        int[] A = new int[] {2, 1};  // --> 1
        logger.info("Result: {} ", solution(2, A));
    }

    private static int solution(int X, int[] A) {
        boolean[] path = new boolean[X];
        int counter = 0;
        int seconds = 0;
        for (int k : A) {
            if (!path[k-1]) {
                path[k-1] = true;
                counter++;
            }
            if (counter == X) {
                break;
            }
            seconds++;
        }
        if (counter == X) {
            return seconds;
        } else {
            return -1;
        }
    }
}