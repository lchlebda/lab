package codility.prefixsums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 2016-05-06
 */
public class GenomicRangeQuery {

    private static final Logger logger = LogManager.getLogger(GenomicRangeQuery.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", solution("CAGCCTA", new int[]{2, 5, 0}, new int[]{4, 5, 6}));  // --> [2,4,1]
    }

    private static int[] solution(String S, int[] P, int[] Q) {
        int len = S.length();
        int[][] arr = new int[len][4];
        int[] result = new int[P.length];

        for(int i = 0; i < len; i++){
            char c = S.charAt(i);
            if(c == 'A') arr[i][0] = 1;
            if(c == 'C') arr[i][1] = 1;
            if(c == 'G') arr[i][2] = 1;
            if(c == 'T') arr[i][3] = 1;
        }
        // compute prefixes
        for(int i = 1; i < len; i++){
            for(int j = 0; j < 4; j++){
                arr[i][j] += arr[i-1][j];
            }
        }

        for(int i = 0; i < P.length; i++){
            int x = P[i];
            int y = Q[i];

            for(int a = 0; a < 4; a++){
                int sub = 0;
                if(x-1 >= 0) sub = arr[x-1][a];
                if(arr[y][a] - sub > 0){
                    result[i] = a+1;
                    break;
                }
            }

        }
        return result;
    }
}