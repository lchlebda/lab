package codility.sorting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
public class NumberOfDiscIntersections {

    private static final Logger logger = LogManager.getLogger(NumberOfDiscIntersections.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", solution(new int[]{1, 5, 2, 1, 4, 0})); // --> 11
    }

    private static int solution(int[] A) {
        Set<Pair> set = new TreeSet<>();
        for (int i=0; i<A.length; i++) {
            set.add(new Pair(i, A[i]));
        }
        Iterator<Pair> iter = set.iterator();
        while (iter.hasNext()) {
            Pair pair = iter.next();

        }

        return 1;
    }

    static class Pair implements Comparable {
        public int index;
        public int radius;

        public Pair(int index, int radius) {
            this.index = index;
            this.radius = radius;
        }

        @Override
        public int compareTo(Object o) {
            Pair pair = (Pair)o;
            if (radius < pair.radius) {
                return 1;
            } else if (radius == pair.radius) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}