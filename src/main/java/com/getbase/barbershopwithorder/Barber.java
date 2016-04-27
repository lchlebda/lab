package com.getbase.barbershopwithorder;

import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Barber implements Runnable {

    private static final Logger logger = LogManager.getLogger(Barber.class);

    private final BlockingQueue<Client> clients;

    public Barber(BlockingQueue<Client> clients) {
        this.clients = clients;
    }

    @Override
    @SneakyThrows
    public void run() {
        while (true) {
            Client client = waitsForClient();
            cut(client);
        }
    }

    private void cut(Client client) throws InterruptedException {
        logger.info("Barber is cutting client's {} hair.", client.getId());
        TimeUnit.SECONDS.sleep(5);
        client.getLock().lock();
        try {
            logger.info("Barber has finished with client {}", client.getId());
            client.condition().signal();
        } finally {
            client.getLock().unlock();
        }
    }

    private Client waitsForClient() throws InterruptedException {
        logger.info("Barber is waiting for client...");
        return clients.take();
    }
}
