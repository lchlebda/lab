package com.getbase.barbershopHilzer;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Terminal {

    @Getter private final Lock lock = new ReentrantLock();
    @Getter private final Condition condition = lock.newCondition();
    @Getter @Setter private Client client;
}
