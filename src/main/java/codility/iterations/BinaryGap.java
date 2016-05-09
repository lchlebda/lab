package codility.iterations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * https://codility.com/programmers/task/binary_gap/
 */
public class BinaryGap {

    private static final Logger logger = LogManager.getLogger(BinaryGap.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", binaryGap(7));
    }

    private static int binaryGap(int N) {
        int max = 0;
        int counter = 0;
        boolean isGapStarted = false;
        do {
            int r = N % 2;
            N = (N-r) / 2;
            if (r == 1) {
                if (counter > max) {
                    max = counter;
                }
                isGapStarted = true;
                counter = 0;
            } else if (isGapStarted) {
                counter++;
            }
        } while (N > 0);

        return max;
    }
}
