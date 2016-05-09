package codility.countingelements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * https://codility.com/programmers/task/missing_integer/
 */
public class MissingInteger {

    private static final Logger logger = LogManager.getLogger(MissingInteger.class);

    public static void main(String[] args) {
        int[] A = new int[]{1, 3, 6, 4, 1, 2};
        logger.info("Result: {} ", solution(A));
    }

    private static int solution(int[] A) {
        boolean[] check = new boolean[A.length];
        for (int i : A) {
            if (i>0 && i<=A.length) {
                if (!check[i-1]) {
                    check[i-1] = true;
                }
            }
        }
        int counter = 0;
        for (boolean i : check) {
            if (i) {
                counter++;
            } else {
                break;
            }
        }
        return counter+1;
    }
}