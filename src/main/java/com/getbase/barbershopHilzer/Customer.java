package com.getbase.barbershopHilzer;

import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer implements Runnable {

    private static final Logger logger = LogManager.getLogger(Customer.class);
    private static final AtomicInteger counter = new AtomicInteger(0);

    private final BlockingQueue<Client> standingClients;
    private final BlockingQueue<Client> sittingClients;
    private final Terminal terminal;

    private Client client;

    public Customer(BlockingQueue<Client> standingClients, BlockingQueue<Client> sittingClients, Terminal terminal) {
        this.standingClients = standingClients;
        this.sittingClients = sittingClients;
        this.terminal = terminal;
    }

    @Override
    @SneakyThrows
    public void run() {
        while (true) {
            client = new Client(counter.incrementAndGet());
            logger.info("New customer {} is comming!", client.getId());
            if (standingClients.size() == BarbershopProblem.numberOfStandingPlaces) {
                exitShop();
            } else {
                enterShop();
                sitOnSofa();
                sitInBarberChair();
                pay();
            }
        }
    }

    @SneakyThrows
    private void enterShop() {
        logger.info("Customer {} enter the shop.", client.getId());
        if (sittingClients.size() == BarbershopProblem.numberOfSeats) {
            standingClients.put(client);
            client.getStandingLock().lock();
            try {
                client.getStandingCondition().await();
            } finally {
                client.getStandingLock().unlock();
            }
        }
    }

    @SneakyThrows
    private void sitOnSofa() {
        standingClients.poll();
        sittingClients.put(client);
        logger.info("Customer {} sit on sofa.", client.getId());
        client.getSofaLock().lock();
        try {
            client.getSofaCondition().await();
        } finally {
            client.getSofaLock().unlock();
        }
    }

    @SneakyThrows
    private void sitInBarberChair() {
        logger.info("Customer {} sit in barber chair.", client.getId());
        client.getBarberLock().lock();
        try {
            client.getBarberCondition().await();
            logger.info("Customer {} FINISH!!!", client.getId());
        } finally {
            client.getBarberLock().unlock();
        }
    }

    @SneakyThrows
    private void pay() {
        terminal.getLock().lock();
        try {
            terminal.setClient(client);
            logger.info("Customer {} is paying.", client.getId());
            client.getBarberLock().lock();
            try {
                client.getBarberCondition().await();
            } finally {
                client.getBarberLock().unlock();
            }
        } finally {
            terminal.getLock().unlock();
        }
    }

    @SneakyThrows
    private void exitShop() {
        logger.info("Barbershop is full, customer {} exit the shop.", client.getId());
        TimeUnit.SECONDS.sleep(10);
    }
}
