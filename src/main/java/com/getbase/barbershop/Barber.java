package com.getbase.barbershop;

import lombok.SneakyThrows;

public class Barber implements Runnable {

    private final Barbershop barbershop;

    public Barber(Barbershop barbershop) {
        this.barbershop = barbershop;
    }

    @Override
    @SneakyThrows
    public void run() {
        while (true) {
            if (barbershop.getNumberOfCustomersInside() == 0) {
                barbershop.waitForCustomers();
            } else {
                barbershop.wakeUpCustomer();
            }
            barbershop.cutHair();
        }
    }
}
