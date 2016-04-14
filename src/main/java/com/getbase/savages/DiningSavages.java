package com.getbase.savages;

import java.util.concurrent.*;

public class DiningSavages {

    public static void main(String... args) {
        int M = 10;
        BlockingQueue<Serving> servings = new ArrayBlockingQueue<Serving>(M);
        CountDownLatch latch = new CountDownLatch(M);
        Cook cook = new Cook(servings, latch, M);
        new Thread(cook).start();

        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i=0; i<20; i++) {
            executorService.submit(new Savage(servings, latch));
        }
    }
}
