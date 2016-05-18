package codility.leader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * https://codility.com/programmers/task/equi_leader/
 */
public class EquiLeader {

    private static final Logger logger = LogManager.getLogger(EquiLeader.class);

    public static void main(String[] args) {
//        logger.info("Result: {} ", solution(new int[]{4, 3, 4, 4, 4, 2})); // --> 2
//        logger.info("Result: {} ", solution(new int[]{4})); // --> 0
//        logger.info("Result: {} ", solution(new int[]{4, -10})); // --> 0
//        logger.info("Result: {} ", solution(new int[]{4, 4})); // --> 1
//        logger.info("Result: {} ", solution(new int[]{4, 4, 4, 4})); // --> 3
//        logger.info("Result: {} ", solution(new int[]{4, 4, 4, 0})); // --> 1
//        logger.info("Result: {} ", solution(new int[]{4, 4, 4, 0, 4})); // --> 3
//        logger.info("Result: {} ", solution(new int[]{1, 2, 3, 4, 5, 0, 0, 0, 0, 0, 0})); // --> 0
//        logger.info("Result: {} ", solution(new int[]{1, 2, 3, 4, 5, 0, 0, 0, 0, 0, 0, 0})); // --> 1
        logger.info("Result: {} ", solution(new int[]{1, 2, -1000000000, -1000000000, -1000000000, -1000000000, -1000000000, 3, 4})); // --> 1
    }

    private static int solution(int[] A) {
        if (A.length == 1) {
            return 0;
        }
        int minOccurances = 1 + A.length/2;
        int leaderCounter = 0;
        int leader = A[0];
        Map<Integer, Integer> map = new HashMap<>();
        int i = 0;
        for (int a : A) {
            if (map.get(a) == null) {
                map.put(a, 1);
            } else {
                int counter = map.get(a) + 1;
                map.put(a, counter);
            }
            if (leaderCounter < map.get(a)) {
                leaderCounter = map.get(a);
                leader = a;
            }
            if (i + minOccurances - leaderCounter > A.length) {
                return 0;
            }
            i++;
        }

        int counter = 0;
        int equiLeaders = 0;
        for (i=0; i<A.length; i++) {
            if (A[i] == leader) {
                counter++;
            }
            if (counter * 2 > i + 1 && (leaderCounter - counter) * 2 > A.length - i - 1) {
                equiLeaders++;
            }
        }

        return equiLeaders;
    }
}