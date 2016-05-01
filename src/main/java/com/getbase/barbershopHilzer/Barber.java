package com.getbase.barbershopHilzer;

import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Time;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Barber implements Runnable {

    private static final Logger logger = LogManager.getLogger(Barber.class);
    private static final AtomicInteger counter = new AtomicInteger(0);

    private final BlockingQueue<Client> standingClients;
    private final BlockingQueue<Client> sittingClients;
    private final Terminal terminal;
    private Client client;
    private final int id;

    public Barber(BlockingQueue<Client> standingClients, BlockingQueue<Client> sittingClients, Terminal terminal) {
        this.standingClients = standingClients;
        this.sittingClients = sittingClients;
        this.terminal = terminal;
        id = counter.incrementAndGet();
    }

    @Override
    public void run() {
        while (true) {
            sleep();
            checkCustomer();
            cutHair();
            acceptPayment();
        }
    }

    @SneakyThrows
    private void sleep() {
        logger.info("Barber {} is sleeping...", id);
        client = sittingClients.take();
    }

    private void checkCustomer() {
        logger.info("Barber {} is checking first customer from sofa.", id);
        client.getSofaLock().lock();
        try {
            client.getSofaCondition().signal();
        } finally {
            client.getSofaLock().unlock();
        }
        Client firstStandingClient = standingClients.peek();
        if (firstStandingClient != null) {
            firstStandingClient.getStandingLock().lock();
            try {
                firstStandingClient.getStandingCondition().signal();
            } finally {
                firstStandingClient.getStandingLock().unlock();
            }
        }
    }

    @SneakyThrows
    private void cutHair() {
        logger.info("Barber {} is cutting customer's {} hair.", id, client.getId());
        TimeUnit.SECONDS.sleep(4);
        client.getBarberLock().lock();
        try {
            client.getBarberCondition().signal();
        } finally {
            client.getBarberLock().unlock();
        }
    }

    @SneakyThrows
    private void acceptPayment() {
        terminal.getClient().getBarberLock().lock();
        logger.info("Barber {} is accepting payment from customer {}.", id, terminal.getClient().getId());
        try {
            TimeUnit.SECONDS.sleep(2);
            terminal.getClient().getBarberCondition().signal();
            logger.info("Customer {} finished payment.", terminal.getClient().getId());
        } finally {
            terminal.getClient().getBarberLock().unlock();
        }
    }
}
