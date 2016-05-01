package com.getbase.barbershopHilzer;

import lombok.Getter;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Client {

    @Getter private final int id;
    @Getter private final Lock standingLock;
    @Getter private final Condition standingCondition;
    @Getter private final Lock sofaLock;
    @Getter private final Condition sofaCondition;
    @Getter private final Lock barberLock;
    @Getter private final Condition barberCondition;

    public Client(int id) {
        this.id = id;
        standingLock = new ReentrantLock();
        standingCondition = standingLock.newCondition();
        sofaLock = new ReentrantLock();
        sofaCondition = sofaLock.newCondition();
        barberLock = new ReentrantLock();
        barberCondition = barberLock.newCondition();
    }

}
