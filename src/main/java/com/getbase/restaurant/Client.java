package com.getbase.restaurant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Client implements Runnable {

    private static final Logger logger = LogManager.getLogger(Client.class);

    private final BlockingQueue<Order> orders;
    private static AtomicInteger counter = new AtomicInteger(0);
    private final int clientId;

    public Client(BlockingQueue<Order> orders) {
        this.orders = orders;
        this.clientId = counter.getAndIncrement();
    }

    @Override
    public void run() {
        try {
            Order order = orderDish();
            orders.put(order);
            order.getCountDownLatch().await();
            logger.info("Client {} received his dish!", clientId);
        } catch (InterruptedException exc) {

        }
    }

    public Order orderDish() throws InterruptedException {
        logger.info("Client {} is coming!", clientId);
        Thread.sleep(1000);
        logger.info("Client {} is waiting for dish.", clientId);

        return new Order(clientId);
    }
}
