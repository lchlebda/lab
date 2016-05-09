package codility.prefixsums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * https://codility.com/programmers/task/count_div/
 */
public class CountDiv {

    private static final Logger logger = LogManager.getLogger(CountDiv.class);

    public static void main(String[] args) {
        logger.info(solution(6, 11, 2)); // --> 3
        logger.info(solution(6, 11, 3)); // --> 2
        logger.info(solution(4, 8, 3)); // --> 1
        logger.info(solution(6, 9, 3)); // --> 2
        logger.info(solution(6, 6, 3)); // --> 1
        logger.info(solution(6, 8, 5)); // --> 0
        logger.info(solution(6, 8, 7)); // --> 1
        logger.info(solution(0, 2, 7)); // --> 1
        logger.info(solution(0, 2, 1)); // --> 3
        logger.info(solution(0, 2, 2)); // --> 2
        logger.info(solution(11, 345, 17)); // --> 20
        logger.info(solution(11, 39, 17)); // --> 2
    }

    public static int solution(int A, int B, int K) {
        if (A != 0) {
            return (B / K) - (A - 1) / K;
        } else {
            return (B / K) + 1;
        }
    }
}