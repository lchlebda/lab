package com.getbase.restaurant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Cheef implements Runnable {

    private static final Logger logger = LogManager.getLogger(Cheef.class);
    private static final AtomicInteger counter = new AtomicInteger(0);

    private final BlockingQueue<Order> waitersCheefsQueue;
    private final int cheefId;

    public Cheef(BlockingQueue<Order> waitersCheefsQueue) {
        this.waitersCheefsQueue = waitersCheefsQueue;
        this.cheefId = counter.getAndIncrement();
    }

    @Override
    public void run() {
        try {
            while (true) {
                cook(waitersCheefsQueue.take());
            }
        } catch (InterruptedException exc) {

        }
    }

    private void cook(Order order) throws InterruptedException {
        logger.info("Cheef {} is cooking for client {}!", cheefId, order.getClientId());
        Thread.sleep(3000);
        order.setDone(true);
        waitersCheefsQueue.put(order);
        logger.info("Cheef {} is now free.", cheefId);
    }
}
