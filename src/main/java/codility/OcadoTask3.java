package codility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 */
public class OcadoTask3 {

    private static final Logger logger = LogManager.getLogger(OcadoTask3.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", solution(2)); // --> 4
        logger.info("Result: {} ", solution(0)); // --> 1
        logger.info("Result: {} ", solution(1)); // --> 4
        logger.info("Result: {} ", solution(8)); // --> 4
        logger.info("Result: {} ", solution(16)); // --> 4
        logger.info("Result: {} ", solution(13)); // --> 8
        logger.info("Result: {} ", solution(40)); // --> 8
        logger.info("Result: {} ", solution(41)); // --> 8
        logger.info("Result: {} ", solution(130)); // --> 16
    }

    private static int solution(int Q) {
        if (Q == 0) {
            return 1;
        }
        int counter = 0;
        for (int i=0; i*i<=Q; i++) {
            int sqrt = (int)Math.sqrt(Q - i*i);
            if (sqrt*sqrt == Q - i*i) {
                if (i == 0 || i*i == Q) {
                    counter += 2;
                } else {
                    counter += 4;
                }
            }
        }

        return counter;
    }
}