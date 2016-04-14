package com.getbase.smokers;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Pusher {

    protected static final Logger logger = LogManager.getLogger(Pusher.class);

    @Getter
    @Setter
    private static volatile boolean tobacco;
    @Getter
    @Setter
    private static volatile boolean matches;
    @Getter
    @Setter
    private static volatile boolean paper;

    public final static Lock tobaccoSmokerLock = new ReentrantLock();
    public final static Condition tobaccoSmokerAvail = tobaccoSmokerLock.newCondition();
    public final static Lock matchesSmokerLock  = new ReentrantLock();
    public final static Condition matchesSmokerAvail = matchesSmokerLock.newCondition();
    public final static Lock paperSmokerLock = new ReentrantLock();
    public final static Condition paperSmokerAvail = paperSmokerLock.newCondition();

    protected static final Lock lock = new ReentrantLock();

    protected void signalPaper(String pusher) {
        paperSmokerLock.lock();
        try {
            logger.info("{} signal paper", pusher);
            paperSmokerAvail.signal();
        } finally {
            paperSmokerLock.unlock();
        }
    }

    protected void signalMatches(String pusher) {
        matchesSmokerLock.lock();
        try {
            logger.info("{} signal matches", pusher);
            matchesSmokerAvail.signal();
        } finally {
            matchesSmokerLock.unlock();
        }
    }

    protected void signalTobacco(String pusher) {
        tobaccoSmokerLock.lock();
        try {
            logger.info("{} signal tobacco", pusher);
            tobaccoSmokerAvail.signal();
        } finally {
            tobaccoSmokerLock.unlock();
        }
    }
}
