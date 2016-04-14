package com.getbase.smokers;

import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class TobaccoPusher extends Pusher implements Runnable {

    private final Lock tobaccoLock;
    private final Condition tobaccoAvail;

    public TobaccoPusher(Lock tobaccoLock, Condition tobaccoAvail) {
        this.tobaccoLock = tobaccoLock;
        this.tobaccoAvail = tobaccoAvail;
    }

    @Override
    @SneakyThrows
    public void run() {
        while (true) {
            tobaccoLock.lock();
            try {
                logger.info("Tobacco pusher is waiting...");
                tobaccoAvail.await();
                lock.lock();
                try {
                    if (isPaper()) {
                        setPaper(false);
                        signalMatches("Tobacco");
                    } else if (isMatches()) {
                        setMatches(false);
                        signalPaper("Tobacco");
                    } else {
                        logger.info("Tobacco set tobacco to true");
                        setTobacco(true);
                    }
                } finally {
                    lock.unlock();
                }
            } finally {
                tobaccoLock.unlock();
            }
        }
    }


}
