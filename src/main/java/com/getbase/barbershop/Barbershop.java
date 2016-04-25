package com.getbase.barbershop;

import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Barbershop {

    private static final Logger logger = LogManager.getLogger(Barbershop.class);
    private static final int numberOfChairs = 10;

    private final Lock barberLock = new ReentrantLock();
    private final Lock customerLock = new ReentrantLock();
    private final Lock chairsLock = new ReentrantLock();

    private final Condition barberCondition = barberLock.newCondition();
    private final Condition customerCondition = customerLock.newCondition();
    private final Condition chairsCondition = chairsLock.newCondition();

    private final Queue<Customer> customers = new LinkedList<>();

    private final AtomicInteger counter = new AtomicInteger(0);

    @SneakyThrows
    public void getHairCut(Customer customer) {
        customerLock.lock();
        try {
            logger.info("Customer {} is getting hair cut.", customer.getId());
            customerCondition.await();
        } finally {
            customerLock.unlock();
        }
    }

    @SneakyThrows
    public void cutHair() {
        logger.info("Barber is cutting hair.");
        TimeUnit.SECONDS.sleep(3);
        customerLock.lock();
        try {
            customerCondition.signal();
        } finally {
            customerLock.unlock();
        }
    }

    @SneakyThrows
    public void sitDown(Customer customer) {
        counter.incrementAndGet();
        chairsLock.lock();
        try {
            logger.info("Customer {} sit down.", customer.getId());
            customers.add(customer);
            chairsCondition.await();
            counter.decrementAndGet();
            getHairCut(customer);
        } finally {
            chairsLock.unlock();
        }
    }

    @SneakyThrows
    public void waitForCustomers() {
        barberLock.lock();
        try {
            logger.info("Barber is going asleep...");
            barberCondition.await();
        } finally {
            barberLock.unlock();
        }
    }

    public void wakeUpBarber(Customer customer) {
        counter.incrementAndGet();
        barberLock.lock();
        try {
            logger.info("Customer {} wake up the barber.", customer.getId());
            barberCondition.signal();
        } finally {
            barberLock.unlock();
        }
    }

    public void wakeUpCustomer() {
        chairsLock.lock();
        try {
            logger.info("Barber is waking up customer!");
            chairsCondition.signal();
        } finally {
            chairsLock.unlock();
        }
    }

    public boolean isFull() {
        return counter.get() == numberOfChairs;
    }

    public int getNumberOfCustomersInside() {
        return counter.get();
    }
}
