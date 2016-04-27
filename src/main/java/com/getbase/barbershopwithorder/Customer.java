package com.getbase.barbershopwithorder;

import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

public class Customer implements Runnable {

    private static final Logger logger = LogManager.getLogger(Customer.class);
    private static final AtomicInteger counter = new AtomicInteger(0);

    private final BlockingQueue<Client> clients;
    private final Lock barberLock;
    private Client client;

    public Customer(BlockingQueue<Client> clients, Lock barberLock) {
        this.clients = clients;
        this.barberLock = barberLock;
    }

    @Override
    @SneakyThrows
    public void run() {
        while (true) {
            entersBarbershop();
            if (!isAnySeatAvailable()) {
                continue;
            }
            waitForBarberAndCut();
        }
    }

    private void waitForBarberAndCut() throws InterruptedException {
        clients.put(client);
        client.getLock().lock();
        try {
            client.condition().await();
        } finally {
            client.getLock().unlock();
        }
        logger.info("Client {} leaves the barbershop.", client.getId());
    }

    private boolean isAnySeatAvailable() {
        barberLock.lock();
        try {
            if (clients.size() == BarbershopProblem.numberOfSeats) {
                logger.info("Client {} leaves the barbershop.", client.getId());
                return false;
            } else {
                return true;
            }
        } finally {
            barberLock.unlock();
        }
    }

    private void entersBarbershop() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        client = new Client(counter.incrementAndGet());
        logger.info("Client {} enters the barbershop.", client.getId());
    }
}
