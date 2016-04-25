package com.getbase.barbershop;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class BarbershopProblem {

    public static void main(String... args) {
        int numberOfCustomers = 20;
        Barbershop barbershop = new Barbershop();
        new Thread(new Barber(barbershop)).start();
        ExecutorService customerService = Executors.newFixedThreadPool(numberOfCustomers);
        for (int i=0; i<numberOfCustomers; i++) {
            customerService.submit(new Customer(barbershop));
        }

    }

}