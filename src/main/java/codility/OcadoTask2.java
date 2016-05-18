package codility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 */
public class OcadoTask2 {

    private static final Logger logger = LogManager.getLogger(OcadoTask2.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", solution(11, 19));  // ---> 4
        logger.info("Result: {} ", solution(1, 2));  // ---> 1
        logger.info("Result: {} ", solution(2, 2));  // ---> 1
        logger.info("Result: {} ", solution(1, 1));  // ---> 0
        logger.info("Result: {} ", solution(12, 18));  // ---> 2
        logger.info("Result: {} ", solution(12, 16));  // ---> 1
        logger.info("Result: {} ", solution(1, 100));  // ---> 25
        logger.info("Result: {} ", solution(1, 1000));  // ---> 168
        logger.info("Result: {} ", solution(1, 5000));  // ---> 669
    }

    private static int solution(int A, int B) {
        int counter = 0;
        int begin = A > 1 ? A : 2;
        for (int i=begin ; i<=B; i++) {
            int j = 2;
            boolean isPrime = true;
            while (j*j <= i) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                } else {
                    j++;
                }
            }
            if (isPrime) {
                counter++;
            }
        }

        return counter;
    }
}