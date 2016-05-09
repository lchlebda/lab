package codility.timecomplexity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * https://codility.com/programmers/task/perm_missing_elem/
 */
public class PermMissingElem {

    private static final Logger logger = LogManager.getLogger(PermMissingElem.class);

    public static void main(String[] args) {
        int[] arr = new int[200111];
        for (int i=0; i<arr.length; i++) {
            arr[i] = i+2;
        }
        logger.info("Result: {} ", solution(arr));
    }

    private static int solution(int[] A) {
        long sum = 0;
        for (int a : A) {
            sum += a;
        }

        return (int)((long)(2 + A.length)*(A.length+1)/2 - sum);
    }
}