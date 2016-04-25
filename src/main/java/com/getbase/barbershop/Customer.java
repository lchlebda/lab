package com.getbase.barbershop;

import lombok.Getter;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer implements Runnable {

    private static final AtomicInteger counter = new AtomicInteger(0);

    @Getter
    private final int id;
    private final Barbershop barbershop;

    public Customer(Barbershop barbershop) {
        this.barbershop = barbershop;
        this.id = counter.incrementAndGet();
    }

    @Override
    @SneakyThrows
    public void run() {
        while (true) {
            if (barbershop.getNumberOfCustomersInside() == 0) {
                barbershop.wakeUpBarber(this);
                barbershop.getHairCut(this);
            } else if (barbershop.isFull()) {
                TimeUnit.SECONDS.sleep(5);
            } else {
                barbershop.sitDown(this);
            }
        }
    }
}
