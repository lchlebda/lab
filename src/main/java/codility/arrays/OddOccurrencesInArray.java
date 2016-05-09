package codility.arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * https://codility.com/programmers/task/odd_occurrences_in_array/
 */
public class OddOccurrencesInArray {

    private static final Logger logger = LogManager.getLogger(OddOccurrencesInArray.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", solution(new int[]{9, 9, 3, 1, 5, 4, 4, 3, 5}));
    }

    private static int solution(int[] A) {
        Map<Integer, Boolean> map = new HashMap<>();
        for (int a : A) {
            if (map.get(a) != null) {
                map.remove(a);
            } else {
                map.put(a, true);
            }
        }

        Set<Map.Entry<Integer, Boolean>> set =  map.entrySet();
        return set.iterator().next().getKey();
    }
}