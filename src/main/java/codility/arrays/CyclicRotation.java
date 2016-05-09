package codility.arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * https://codility.com/programmers/task/cyclic_rotation/
 */
public class CyclicRotation {

    private static final Logger logger = LogManager.getLogger(CyclicRotation.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", cyclicRotation(new int[]{3}, 0));
    }

    private static int[] cyclicRotation(int[] A, int K) {
        int N = A.length;
        if (N == 0 || K == 0) {
            return A;
        }
        if (N > 100) {
            throw new IllegalArgumentException("Wrong size of array provided!");
        }
        if (K < 0 || K > 100) {
            throw new IllegalArgumentException("Number of rotations doesn't fit the set [0..100]!");
        }
        for (int a : A) {
            if (a > 1000 || a < -1000) {
                throw new IllegalArgumentException("At least one number in array doesn't fit the set [-1000..1000]!");
            }
        }

        int[] result = new int[N];
        for (int i=0; i<K; i++) {
            result[0] = A[N-1];
            System.arraycopy(A, 0, result, 1, N - 1);
            System.arraycopy(result, 0, A, 0, N);
        }

        return result;
    }
}