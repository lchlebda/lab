package codility.countingelements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * https://codility.com/programmers/task/perm_check/
 */
public class PermCheck {

    private static final Logger logger = LogManager.getLogger(PermCheck.class);

    public static void main(String[] args) {
       // int[] A = new int[]{4, 1, 3, 2};  // --> 1
//        int[] A = new int[]{4, 1, 5, 2};  // --> 0
//        int[] A = new int[]{1};  // --> 1
//        int[] A = new int[]{100};  // --> 0
//        int[] A = new int[]{1, 1, 1};  // --> 0
//        int[] A = new int[]{5, 4, 3, 2, 1};  // --> 1
        int[] A = new int[]{5, 4, 3, 2, 6};  // --> 0
        logger.info("Result: {} ", solution(A));
    }

    private static int solution(int[] A) {
        boolean[] perm = new boolean[A.length];
        int counter = 0;
        for (int i : A) {
            if (i > A.length) {
                return 0;
            }
            if (!perm[i-1]) {
                perm[i-1] = true;
                counter++;
            } else {
                return 0;
            }
        }

        if (counter != A.length) {
            return 0;
        } else {
            return 1;
        }
    }
}