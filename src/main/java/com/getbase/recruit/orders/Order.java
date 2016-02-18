package com.getbase.recruit.orders;

import java.math.BigDecimal;

public interface Order extends Comparable<Order> {

    void process();
    BigDecimal getPrice();
    BigDecimal getTax();
    int getItemId();
    int getCustomerId();

}
