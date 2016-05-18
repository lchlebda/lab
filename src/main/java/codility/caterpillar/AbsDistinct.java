package codility.caterpillar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * https://codility.com/programmers/task/abs_distinct/
 */
public class AbsDistinct {

    private static final Logger logger = LogManager.getLogger(AbsDistinct.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", solution(new int[]{-5, -3, -1, 0, 3, 6})); // --> 5
        logger.info("Result: {} ", solution(new int[]{1})); // --> 1
        logger.info("Result: {} ", solution(new int[]{-1, 1})); // --> 1
        logger.info("Result: {} ", solution(new int[]{-1, 1, 1, 1, 1})); // --> 1
    }

    public static int solution(int[] A) {
        Map<Integer, Boolean> map = new HashMap<>();
        int counter = 0;
        for (int a : A) {
            if (map.get(Math.abs(a)) == null) {
                map.put(Math.abs(a), true);
                counter++;
            }
        }

        return counter;
    }
}