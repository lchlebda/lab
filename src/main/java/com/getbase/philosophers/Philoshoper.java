package com.getbase.philosophers;

import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class Philoshoper implements Runnable {

    private static final Logger logger = LogManager.getLogger(Philoshoper.class);
    private final int id;
    private final Lock leftFork;
    private final Lock rightFork;

    public Philoshoper(int id, Lock leftFork, Lock rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    @SneakyThrows
    public void run() {
        while (true) {
            logger.info("Philisopher {} is thinking...", id);
            TimeUnit.SECONDS.sleep(1);
            leftFork.lock();
            try {
                logger.info("Philisopher {} has left fork and is waiting for right.", id);
                TimeUnit.MILLISECONDS.sleep(100); //DEADLOCK!!!!
                if (rightFork.tryLock()) {  // IT RESOLVE DEADLOCK :)
                    try {
                        logger.info("Philisopher {} has both forks.", id);
                        logger.info("Philisopher {} is eating!", id);
                        TimeUnit.MILLISECONDS.sleep(500);
                    } finally {
                        rightFork.unlock();
                        logger.info("Philisopher {} has received right fork.", id);
                    }
                }
            } finally {
                leftFork.unlock();
                logger.info("Philisopher {} has received left fork.", id);
            }
        }
    }
}
