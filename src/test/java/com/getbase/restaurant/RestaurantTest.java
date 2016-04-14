package com.getbase.restaurant;

import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;

public class RestaurantTest {

    public static void main(String... args) {
        BlockingQueue<Order> clientsWaitersQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Order> waitersCheefsQueue = new LinkedBlockingQueue<>();

        ExecutorService clientsThreadPool = Executors.newFixedThreadPool(5);
        for (int i=0; i<5; i++) {
            clientsThreadPool.submit(new Client(clientsWaitersQueue));
        }
        ExecutorService waitersThreadPool = Executors.newFixedThreadPool(5);
        for (int i=0; i<5; i++) {
            waitersThreadPool.submit(new Waiter(clientsWaitersQueue, waitersCheefsQueue));
        }
        ExecutorService cheefsThreadPool = Executors.newFixedThreadPool(2);
        for (int i=0; i<2; i++) {
            cheefsThreadPool.submit(new Cheef(waitersCheefsQueue));
        }
    }
}
