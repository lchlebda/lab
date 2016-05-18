package codility.euclideanalg;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * https://codility.com/programmers/task/chocolates_by_numbers/
 */
public class ChocolatesByNumbers {

    private static final Logger logger = LogManager.getLogger(ChocolatesByNumbers.class);

    public static void main(String[] args) {
//        logger.info("Result: {} ", solution(10, 4)); // --> 5
//        logger.info("Result: {} ", solution(10, 10)); // --> 1
//        logger.info("Result: {} ", solution(11, 7)); // --> 11
        logger.info("Result: {} ", solution(947853, 4453)); // --> 947853
    }

    private static int solution(int N, int M) {
        int a, b;
        if (N >= M) {
            a = N;
            b = M;
        } else {
            a = M;
            b = N;
        }
        while (b != 0) {
            int c = a % b;
            a = b;
            b = c;
        }
        long nww = ((long)N * M)/a;
        return (int)(nww/M);
    }
}