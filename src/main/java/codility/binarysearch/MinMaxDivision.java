package codility.binarysearch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 * https://codility.com/programmers/task/min_max_division/
 */
public class MinMaxDivision {

    private static final Logger logger = LogManager.getLogger(MinMaxDivision.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", solution(3, 5, new int[]{2, 1, 5, 1, 2, 2, 2})); // --> 6
    }

    private static int solution(int K, int M, int[] A) {
        int max = 0;
        int sum = 0;
        for (int a : A) {
            sum += a;
            if (a > max) {
                max = a;
            }
        }

        int downBorder = max;
        int upBorder = sum;
        int result = upBorder;
        while (downBorder <= upBorder) {
            int mid = (downBorder + upBorder)/2;
            if (divisionSolvable(K - 1, mid, A)) {
                upBorder = mid - 1;
                result = mid;
            } else {
                downBorder = mid + 1;
            }
        }

        return result;
    }

    private static boolean divisionSolvable(int K, int sum, int[] A) {
        int currentSum = 0;
        for (int a : A) {
            currentSum += a;
            if (currentSum > sum) {
                currentSum = a;
                K--;
            }
            if (K < 0) {
                return false;
            }
        }

        return true;
    }
}