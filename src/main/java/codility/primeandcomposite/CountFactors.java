package codility.primeandcomposite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * https://codility.com/programmers/task/count_factors/
 */
public class CountFactors {

    private static final Logger logger = LogManager.getLogger(CountFactors.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", solution(24)); // --> 8
        logger.info("Result: {} ", solution(1));  // --> 1
        logger.info("Result: {} ", solution(2));  // --> 2
        logger.info("Result: {} ", solution(11)); // --> 2
        logger.info("Result: {} ", solution(9));  // --> 3
        logger.info("Result: {} ", solution(Integer.MAX_VALUE));
        logger.info("Result: {} ", solution(1000000000));
    }

    private static int solution(int N)  {
        if (N == 1) {
            return 1;
        }
        int counter = 2;
        int i = 2;
        while ((long)i*i < N) {
            if (N % i == 0) {
                counter += 2;
            }
            i++;
        }
        if (i*i == N) {
            counter++;
        }

        return counter;
    }
}