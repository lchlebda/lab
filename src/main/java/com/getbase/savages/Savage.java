package com.getbase.savages;

import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Savage implements Runnable {

    private static final Logger logger = LogManager.getLogger(Savage.class);
    private static final AtomicInteger counter = new AtomicInteger(0);

    private final BlockingQueue<Serving> servings;
    private final CountDownLatch latch;
    private final int id;

    public Savage(BlockingQueue<Serving> servings, CountDownLatch latch) {
        this.servings = servings;
        this.latch = latch;
        this.id = counter.getAndIncrement();
    }

    @Override
    public void run() {
        while (true) {
            getServingFromPot();
            eat();
        }
    }

    @SneakyThrows
    private void eat() {
        logger.info("Savage {} is eating.", id);
        TimeUnit.SECONDS.sleep(2);
    }

    @SneakyThrows
    private void getServingFromPot() {
        logger.info("Savage {} is waiting for pot.", id);
        servings.take();
        latch.countDown();
    }
}
