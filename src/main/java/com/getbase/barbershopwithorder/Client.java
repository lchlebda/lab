package com.getbase.barbershopwithorder;

import lombok.Getter;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Client {

    @Getter
    private final int id;
    private final Lock lock;
    private final Condition condition;

    public Client(int id) {
        this.id = id;
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    public Lock getLock() {
        return lock;
    }

    public Condition condition() {
        return condition;
    }
}
