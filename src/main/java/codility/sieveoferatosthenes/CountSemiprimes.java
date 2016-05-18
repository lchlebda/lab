package codility.sieveoferatosthenes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * https://codility.com/programmers/task/count_semiprimes/
 */
public class CountSemiprimes {

    private static final Logger logger = LogManager.getLogger(CountSemiprimes.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", solution(26, new int[]{1, 4, 16}, new int[]{26, 10, 20})); // --> [10, 4, 0]
        logger.info("Result: {} ", solution(26, new int[]{1, 4, 26}, new int[]{3, 5, 26})); // --> [0, 1, 1]
    }

    public static int[] solution(int N, int[] P, int[] Q) {
        boolean[] sieve = new boolean[N / 2 + 1];
        for (int i = 2; i < sieve.length; i++) {
            sieve[i] = true;
        }
        for (int i = 2; i < sieve.length; i++) {
            for (int j = i; i * j < sieve.length; j++) {
                sieve[i * j] = false;
            }
        }

        int[] semiprimes = new int[N + 1];
        for (int i = 0; i < sieve.length; i++) {
            if (!sieve[i]) {
                continue;
            }
            for (int j = i; j < sieve.length && i*j<=N; j++) {
                if (sieve[j]) {
                    semiprimes[i * j] = 1;
                }
            }
        }
        int[] semiprimesAppeared = new int[N + 1];
        semiprimesAppeared[0] = 0;
        for (int i = 1; i < semiprimesAppeared.length; i++) {
            semiprimesAppeared[i] = semiprimesAppeared[i - 1] + semiprimes[i];
        }

        int[] result = new int[P.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = semiprimesAppeared[Q[i]] - semiprimesAppeared[P[i] - 1];
        }

        return result;
    }
}