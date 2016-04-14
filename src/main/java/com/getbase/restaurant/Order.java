package com.getbase.restaurant;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.CountDownLatch;

public class Order {

    @Getter
    private final int clientId;
    @Getter
    private final CountDownLatch countDownLatch = new CountDownLatch(1);
    @Getter @Setter
    private volatile boolean done = false;

    public Order(int clientId) {
        this.clientId = clientId;
    }

}
