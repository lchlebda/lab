package labs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LinkedListTest {

    private static final Logger logger = LogManager.getLogger(LinkedListTest.class);

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        list.addFirst("fourth before first");
        logger.info(list.toString());
        logger.info(list.get("second"));
        logger.info(list.size());
        list.removeLast();
        list.removeFirst();
        logger.info(list.toString());
        logger.info(list.get("first"));
        logger.info(list.get("fffff"));
        logger.info(list.size());
    }
}
