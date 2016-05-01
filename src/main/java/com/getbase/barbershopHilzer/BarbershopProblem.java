package com.getbase.barbershopHilzer;

import lombok.SneakyThrows;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BarbershopProblem {

    public static final int numberOfSeats = 4;
    public static final int numberOfStandingPlaces = 16;

    @SneakyThrows
    public static void main(String... args) {
        int numberOfCustomers = 40;
        int numberOfBarbers = 3;
//        TimeUnit.SECONDS.sleep(10);
        BlockingQueue<Client> standingClients = new ArrayBlockingQueue<Client>(numberOfStandingPlaces);
        BlockingQueue<Client> sittingClients = new ArrayBlockingQueue<Client>(numberOfSeats);
        Terminal terminal = new Terminal();

        ExecutorService barberService = Executors.newFixedThreadPool(numberOfBarbers);
        for (int i=0; i<numberOfBarbers; i++) {
            barberService.submit(new Barber(standingClients, sittingClients, terminal));
        }

        TimeUnit.SECONDS.sleep(2);

        ExecutorService customerService = Executors.newFixedThreadPool(numberOfCustomers);
        for (int i=0; i<numberOfCustomers; i++) {
            customerService.submit(new Customer(standingClients, sittingClients, terminal));
        }
    }
}
