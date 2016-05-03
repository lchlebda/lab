package codility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * https://codility.com/programmers/task/tree_height/
 */
public class TreeHeight {

    private static final Logger logger = LogManager.getLogger(TreeHeight.class);

    public static void main(String[] args) {
        Tree tree = new Tree(5, new Tree(3, new Tree(20, null, null), new Tree(21, null, null)), new Tree(10, new Tree(1, null, null), null));
//        Tree tree = new Tree(5, new Tree(3, new Tree(20, new Tree(14, null, null), null), new Tree(21, null, null)), new Tree(10, new Tree(1, null, null), null));
//        Tree tree = new Tree(5, new Tree(4, null, null), new Tree(6, new Tree(4, null, null), new Tree(7, new Tree(4, null, null), new Tree(8, null, new Tree(4, null, null)))));
        logger.info("Result: {} ", solution(tree));
    }

    static int max = 0;

    private static int solution(Tree T) {
        if (T == null) {
            return -1;
        }
        if (T.l == null && T.r == null) {
            return 0;
        }

        findPath(T, 0);
        return max-1;
    }

    private static int findPath(Tree T, int counter) {
        counter++;
        if (T.l != null) {
            counter = findPath(T.l, counter);
        }
        if (T.r != null) {
            counter = findPath(T.r, counter);
        }
        if (counter > max) {
            max = counter;
        }

        return counter-1;
    }

    static class Tree {

        public int x;
        public Tree l;
        public Tree r;

        public Tree(int x, Tree left, Tree right) {
            this.x = x;
            this.l = left;
            this.r = right;
        }
    }
}