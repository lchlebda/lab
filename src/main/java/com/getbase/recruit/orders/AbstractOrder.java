package com.getbase.recruit.orders;

import java.math.BigDecimal;

public abstract class AbstractOrder implements Order {

    protected Order order;

    public AbstractOrder(Order order) {
        this.order = order;
    }

    @Override
    public void process() {
        order.process();
    }

    @Override
    public BigDecimal getPrice() {
        return order.getPrice();
    }

    @Override
    public BigDecimal getTax() {
        return order.getTax();
    }

    @Override
    public int getItemId() {
        return order.getItemId();
    }

    @Override
    public int getCustomerId() {
        return order.getCustomerId();
    }

    @Override
    public int compareTo(Order o) {
        return order.compareTo(o);
    }
}
