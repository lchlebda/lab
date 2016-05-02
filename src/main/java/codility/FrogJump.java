package codility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * https://codility.com/programmers/task/frog_jmp/
 */
public class FrogJump {

    private static final Logger logger = LogManager.getLogger(FrogJump.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", solution(10, 10, 30));
    }

    private static int solution(int X, int Y, int D) {
        int mod = (Y-X) % D;
        if (mod != 0) {
            return (Y-X)/D + 1;
        } else {
            return (Y-X)/D;
        }
    }
}