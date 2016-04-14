package com.getbase.smokers;

import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class PaperPusher extends Pusher implements Runnable {

    private final Lock paperLock;
    private final Condition paperAvail;

    public PaperPusher(Lock paperLock, Condition paperAvail) {
        this.paperLock = paperLock;
        this.paperAvail = paperAvail;
    }

    @Override
    @SneakyThrows
    public void run() {
        while (true) {
            paperLock.lock();
            try {
                logger.info("Paper pusher is waiting...");
                paperAvail.await();
                lock.lock();
                try {
                    if (isTobacco()) {
                        setTobacco(false);
                        signalMatches("Paper");
                    } else if (isMatches()) {
                        setMatches(false);
                        signalTobacco("Paper");
                    } else {
                        logger.info("Paper set paper to true");
                        setPaper(true);
                    }
                } finally {
                    lock.unlock();
                }
            } finally {
                paperLock.unlock();
            }
        }
    }
}
