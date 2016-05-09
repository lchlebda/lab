package codility.countingelements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * https://codility.com/programmers/task/max_counters/
 */
public class MaxCounters {

    private static final Logger logger = LogManager.getLogger(MaxCounters.class);

    public static void main(String[] args) {
        int[] A = new int[]{3, 4, 4, 6, 1, 4, 4};  //--> 3, 2, 2, 4, 2
//        int[] A = new int[]{6, 6, 6}; // --> 0, 0, 0, 0, 0
//        int[] A = new int[]{6, 1, 6, 6, 6}; // --> 1, 1, 1, 1, 1
//        int[] A = new int[]{6, 1, 1, 6, 1, 6, 1, 6}; // --> 4, 4, 4, 4, 4
        logger.info("Result: {} ", solution(5, A));
    }

    public static int[] solution(int N, int[] A) {
        int max = 0;
        int addMaxToEveryCounter = 0;
        int[] result = new int[N];
        boolean allEquals = false;
        for (int i : A) {
            if (i == N + 1) {
                if (!allEquals) {
                    addMaxToEveryCounter += max;
                    result = new int[N];
                    max = 0;
                }
                allEquals = true;
            } else {
                result[i - 1]++;
                if (result[i - 1] > max) {
                    max = result[i - 1];
                }
                allEquals = false;
            }
        }
        for (int i = 0; i < N; i++) {
            result[i] += addMaxToEveryCounter;
        }

        return result;
    }
}