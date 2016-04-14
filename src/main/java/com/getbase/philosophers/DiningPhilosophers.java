package com.getbase.philosophers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {

    public static void main(String... args) {
        Lock first = new ReentrantLock();
        Lock second = new ReentrantLock();
        Lock third = new ReentrantLock();
        Lock fourth = new ReentrantLock();
        Lock fifth = new ReentrantLock();

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(new Philoshoper(1, first, second));
        executorService.submit(new Philoshoper(2, second, third));
        executorService.submit(new Philoshoper(3, third, fourth));
        executorService.submit(new Philoshoper(4, fourth, fifth));
        executorService.submit(new Philoshoper(5, fifth, first));

    }
}
