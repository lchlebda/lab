package codility.caterpillar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;


/**
 * https://codility.com/programmers/task/count_triangles/
 */
public class CountTriangles {

    private static final Logger logger = LogManager.getLogger(CountTriangles.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", solution(new int[]{10, 2, 5, 1, 8, 12})); // --> 4
        logger.info("Result: {} ", solution(new int[]{})); // --> 0
        logger.info("Result: {} ", solution(new int[]{1})); // --> 0
        logger.info("Result: {} ", solution(new int[]{1, 2})); // --> 0
        logger.info("Result: {} ", solution(new int[]{1, 2, 3})); // --> 0
        logger.info("Result: {} ", solution(new int[]{3, 4, 5})); // --> 1
    }

    private static int solution(int[] A) {
        Arrays.sort(A);
        int counter = 0;
        for (int i = 0; i < A.length - 2; i++) {
            int j = i + 1;
            int k = i + 2;
            while (k < A.length) {
                if (A[i] + A[j] > A[k]) {
                    counter += k - j;
                    k++;
                } else {
                    j++;
                    if (k == j) {
                        k++;
                    }
                }
            }
        }

        return counter;
    }
}