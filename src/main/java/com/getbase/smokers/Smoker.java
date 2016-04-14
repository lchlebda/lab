package com.getbase.smokers;

import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Smoker implements Runnable {

    private static final Logger logger = LogManager.getLogger(Smoker.class);

    private final Ingredient ingredient;
    private final Lock agentLock;
    private final Condition agentCond;
    private final Lock ingredientLock;
    private final Condition ingredientCond;

    public Smoker(Ingredient ingredient, Lock agentLock, Condition agentCond, Lock ingredientLock, Condition ingredientCond) {
        this.ingredient = ingredient;
        this.agentLock = agentLock;
        this.agentCond = agentCond;
        this.ingredientLock = ingredientLock;
        this.ingredientCond = ingredientCond;
    }

    @Override
    @SneakyThrows
    public void run() {
        while (true) {
            logger.info("Smoker with {} is waiting...", ingredient);
            ingredientLock.lock();
            try {
                logger.info("Smoker with {} aquired ingredient lock", ingredient);
                ingredientCond.await();
                logger.info("Smoker with {} is making a cigarette", ingredient);
                TimeUnit.MILLISECONDS.sleep(1000);
            } finally {
                ingredientLock.unlock();
                logger.info("Smoker with {} released ingredient lock", ingredient);
            }

            agentLock.lock();
            try {
                agentCond.signal();
            } finally {
                agentLock.unlock();
            }

            logger.info("Smoker with {} is smoking a cigarette", ingredient);
            TimeUnit.MILLISECONDS.sleep(3000);
        }
    }
}
