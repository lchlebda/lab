package com.getbase.barbershopwithorder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BarbershopProblem {

    public static final int numberOfSeats = 10;

    public static void main(String... args) {
        int numberOfCustomers = 20;
        BlockingQueue<Client> clients = new ArrayBlockingQueue<Client>(numberOfSeats);
        Lock barberLock = new ReentrantLock();

        new Thread(new Barber(clients)).start();
        ExecutorService customerService = Executors.newFixedThreadPool(numberOfCustomers);
        for (int i=0; i<numberOfCustomers; i++) {
            customerService.submit(new Customer(clients, barberLock));
        }
    }
}
