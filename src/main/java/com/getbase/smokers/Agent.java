package com.getbase.smokers;

import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Agent implements Runnable {

    private static final Logger logger = LogManager.getLogger(Agent.class);
    private final Random random = new Random();

    @Getter
    private final Lock agentLock = new ReentrantLock();
    @Getter
    private final Condition agentCond = agentLock.newCondition();
    @Getter
    private final Lock tobaccoLock = new ReentrantLock();
    @Getter
    private final Lock matchesLock = new ReentrantLock();
    @Getter
    private final Lock paperLock = new ReentrantLock();

    @Getter
    private final Condition tobaccoAvail = tobaccoLock.newCondition();
    @Getter
    private final Condition matchesAvail = matchesLock.newCondition();
    @Getter
    private final Condition paperAvail = paperLock.newCondition();

    private final Ingredient[][] ingredients = new Ingredient[][] {
            {Ingredient.TOBACCO, Ingredient.PAPER},
            {Ingredient.TOBACCO, Ingredient.MATCHES},
            {Ingredient.PAPER, Ingredient.MATCHES}
    };

    private Ingredient[] availableIngredients;

    @Override
    @SneakyThrows
    public void run() {
        while (true) {
            TimeUnit.MILLISECONDS.sleep(1000);
            availableIngredients = ingredients[random.nextInt(3)];
            logger.info("Agent gives two ingredients: {} and {}", availableIngredients[0], availableIngredients[1]);
            for (Ingredient ingredient : availableIngredients) {
                switch(ingredient) {
                    case TOBACCO: signalTobacco(); break;
                    case MATCHES: signalMatches(); break;
                    case PAPER: signalPaper(); break;
                }
            }
            agentLock.lock();
            try {
                agentCond.await(3, TimeUnit.SECONDS);
            } finally {
                agentLock.unlock();
            }
        }
    }

    private void signalTobacco() {
        tobaccoLock.lock();
        try {
          tobaccoAvail.signalAll();
        } finally {
            tobaccoLock.unlock();
        }
    }

    private void signalMatches() {
        matchesLock.lock();
        try {
            matchesAvail.signalAll();
        } finally {
            matchesLock.unlock();
        }
    }

    private void signalPaper() {
        paperLock.lock();
        try {
            paperAvail.signalAll();
        } finally {
            paperLock.unlock();
        }
    }

}
