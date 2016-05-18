package codility.leader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * https://codility.com/programmers/task/dominator/
 */
public class Dominator {

    private static final Logger logger = LogManager.getLogger(Dominator.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", solution(new int[]{3, 4, 3, 2, 3, -1, 3, 3})); // -->  7
        logger.info("Result: {} ", solution(new int[]{3, 4, 3, 2, 3, 0})); // --> -1
        logger.info("Result: {} ", solution(new int[]{3, 4, 3, 2, 3, 3})); // --> 5
        logger.info("Result: {} ", solution(new int[]{})); // --> -1
        logger.info("Result: {} ", solution(new int[]{2})); // --> 0
        logger.info("Result: {} ", solution(new int[]{2, 3})); // --> -1
        logger.info("Result: {} ", solution(new int[]{2, 2})); // --> 1
    }

    private static int solution(int[] A) {
        if (A.length == 0) {
            return -1;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int max = 1;
        int id = 0;
        for (int i=0; i<A.length; i++) {
            Integer elem = map.get(A[i]);
            if (elem == null) {
                map.put(A[i], 1);
            } else {
                int counter = elem + 1;
                map.put(A[i], counter);
                if (counter > max) {
                    max = counter;
                    id = i;
                }
            }
            if (A.length/2 + 1 > A.length-i-1+max) {
                return -1;
            }
        }

        return id;
    }
}