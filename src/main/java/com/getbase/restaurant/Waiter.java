package com.getbase.restaurant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Waiter implements Runnable {

    private static final Logger logger = LogManager.getLogger(Waiter.class);

    private static final AtomicInteger counter = new AtomicInteger(0);
    private final BlockingQueue<Order> clientsWaiterQueue;
    private final BlockingQueue<Order> waitersCheefsQueue;
    private final int waiterId;

    public Waiter(BlockingQueue<Order> clientsWaiterQueue, BlockingQueue<Order> waitersCheefsQueue) {
        this.clientsWaiterQueue = clientsWaiterQueue;
        this.waitersCheefsQueue = waitersCheefsQueue;
        this.waiterId = counter.getAndIncrement();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Order orderFromClient = clientsWaiterQueue.poll();
                if (orderFromClient != null) {
                    takeOrderFromClient(orderFromClient);
                }
                Order orderFromCheef = waitersCheefsQueue.peek();
                if (orderFromCheef != null && orderFromCheef.isDone()) {
                    takeReadyOrder(waitersCheefsQueue.poll());
                }
            }
        } catch (InterruptedException exc) {

        }
    }

    private void takeOrderFromClient(Order order) throws InterruptedException {
        logger.info("Waiter {} is taking an order from client {}!", waiterId, order.getClientId());
        Thread.sleep(500);
        waitersCheefsQueue.add(order);
        logger.info("Waiter {} is now free.", waiterId);
    }

    private void takeReadyOrder(Order order) throws InterruptedException {
        logger.info("Waiter {} is taking ready order for client {}!", waiterId, order.getClientId());
        Thread.sleep(500);
        order.getCountDownLatch().countDown();
        logger.info("Waiter {} is now free.", waiterId);
    }
}
