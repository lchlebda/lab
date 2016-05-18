package codility.fibonacci;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// TODO!

/**
 * https://codility.com/programmers/task/fib_frog/
 */
public class FibFrog {

    private static final Logger logger = LogManager.getLogger(FibFrog.class);

    public static void main(String[] args) {
        logger.info("Result: {} ", solution(new int[] {0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0}));  // --> 3
        logger.info("Result: {} ", solution(new int[] {0, 0, 0, 0, 0}));  // --> -1
        logger.info("Result: {} ", solution(new int[] {0, 0, 0, 0}));  // --> 1
        logger.info("Result: {} ", solution(new int[] {1, 1, 0, 0, 0}));  // --> 2
        logger.info("Result: {} ", solution(new int[] {0, 0, 1, 1, 1, 0, 1, 0, 0, 0}));  // --> 2
        logger.info("Result: {} ", solution(new int[] {1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0}));  // --> 3
    }

    private static int solution(int[] A) {
        if (A.length <= 2) {
            return 1;
        }
        List<Integer> fibb = new ArrayList<>();
        fibb.add(1);
        fibb.add(2);
        int counter = 2;
        int nextFibb;
        while ((nextFibb = fibb.get(counter-2) + fibb.get(counter-1)) <= A.length + 1) {
            fibb.add(nextFibb);
            counter++;
        }

        int size = fibb.size();
        counter = 0;
        Deque<Leave> path = new ArrayDeque<>();
        path.push(new Leave(-1, size-1));
        int currentId = -1;
        int nextStep = size-1;
        while (currentId <= A.length) {
            int f1 = nextStep;
            currentId = path.peek().id;
            while (currentId + fibb.get(f1) >= A.length + 1) {
                f1--;
            }
            if (currentId + fibb.get(f1) == A.length) {
                counter++;
                break;
            }
            while (f1 >= 0 && A[currentId + fibb.get(f1)] == 0) {
                f1--;
            }
            if (f1 < 0) {
                counter--;
                Leave leave = path.poll();
                if (leave.id == -1) {
                    return -1;
                }
                nextStep = leave.lastFibb - 1;
                continue;
            }
            counter++;
            currentId = currentId + fibb.get(f1);
            path.push(new Leave(currentId, f1));
            nextStep = size - 1;
        }

        return counter > 0 ? counter : -1;
    }

    private static class Leave {
        public int id;
        public int lastFibb;

        public Leave(int id, int lastFibb) {
            this.id = id;
            this.lastFibb = lastFibb;
        }
    }
}