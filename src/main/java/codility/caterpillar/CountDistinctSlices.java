package codility.caterpillar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * https://codility.com/programmers/task/count_distinct_slices/
 */
public class CountDistinctSlices {

    private static final Logger logger = LogManager.getLogger(CountDistinctSlices.class);

    public static void main(String[] args) {
        logger.info(solution(6, new int[]{3, 4, 5, 5, 2})); // --> 9
        logger.info(solution(6, new int[]{6})); // --> 1
        logger.info(solution(6, new int[]{5, 5})); // --> 2
        logger.info(solution(6, new int[]{6, 5, 4, 3, 2, 1})); // --> 21
        logger.info(solution(6, new int[]{1, 1, 1, 1, 1})); // --> 5
        logger.info(solution(6, new int[]{1, 1, 2, 2, 3, 3})); // --> 8
        logger.info(solution(100000, new int[]{100000, 100000})); // --> 2
        logger.info(solution(100000, new int[]{1, 1, 2, 3, 3, 3})); // --> 9
        logger.info(solution(100000, new int[]{1, 2, 3, 4, 2, 4})); // --> 15
        logger.info(solution(100000, new int[]{1, 2, 3, 4, 2, 3, 4})); // --> 19
        logger.info(solution(100000, new int[]{1, 2, 5, 4, 2, 3, 4})); // --> 20
    }

    public static int solution(int M, int[] A) {
        long counter = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int begin = 0;
        for (int i = 0; i < A.length; i++) {
            Integer id = map.get(A[i]);
            if (id != null && id >= begin) {
                begin = id + 1;
            }
            counter += i - begin + 1;
            map.put(A[i], i);
        }

        return (int) (counter > 1000000000 ? 1000000000 : counter);
    }
}