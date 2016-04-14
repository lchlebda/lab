package com.getbase.savages;

import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Cook implements Runnable {

    private static final Logger logger = LogManager.getLogger(Cook.class);

    private final BlockingQueue<Serving> servings;
    private final CountDownLatch latch;
    private final int M;

    public Cook(BlockingQueue<Serving> servings, CountDownLatch latch, int M) {
        this.servings = servings;
        this.latch = latch;
        this.M = M;
    }

    @Override
    @SneakyThrows
    public void run() {
        while (true) {
            putServingInPot();
            latch.await();
        }
    }

    private void putServingInPot() throws InterruptedException {
        logger.info("Cook is started preparing a dish.");
        TimeUnit.SECONDS.sleep(5);
        List<Serving> list = new ArrayList<>(M);
        for (int i=0; i<M; i++) {
            list.add(new Serving());
        }
        logger.info("Dish is ready to served!");
        servings.addAll(list);
    }
}
