package com.getbase.smokers;

import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MatchesPusher extends Pusher implements Runnable {

    private final Lock matchesLock;
    private final Condition matchesAvail;

    public MatchesPusher(Lock matchesLock, Condition matchesAvail) {
        this.matchesLock = matchesLock;
        this.matchesAvail = matchesAvail;
    }


    @Override
    @SneakyThrows
    public void run() {
        while (true) {
            matchesLock.lock();
            try {
                logger.info("Matches pusher is waiting...");
                matchesAvail.await();
                lock.lock();
                try {
                    if (isPaper()) {
                        setPaper(false);
                        signalTobacco("Matches");
                    } else if (isTobacco()) {
                        setTobacco(false);
                        signalPaper("Matches");
                    } else {
                        logger.info("Matches set matches to true");
                        setMatches(true);
                    }
                } finally {
                    lock.unlock();
                }
            } finally {
                matchesLock.unlock();
            }
        }
    }
}
