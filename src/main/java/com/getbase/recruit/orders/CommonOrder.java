package com.getbase.recruit.orders;

import com.getbase.recruit.SeriousEnterpriseEventBus;
import com.getbase.recruit.SeriousEnterpriseEventBusLookup;
import com.getbase.recruit.TaxCalculationsHelper;

import java.math.BigDecimal;

public class CommonOrder implements Order {

    private final int itemId;
    private final int customerId;
    private final BigDecimal price;

    public CommonOrder(int itemId, int customerId, BigDecimal price) {
        this.itemId = itemId;
        this.customerId = customerId;
        this.price = price;
    }

    @Override
    public void process() {
        SeriousEnterpriseEventBus seeb = SeriousEnterpriseEventBusLookup.seeb;
        seeb.sendEvent("Order processing started");
        seeb.sendEvent("Initiate shipment");
        seeb.sendEvent("Order processing finished");
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public BigDecimal getTax() {
        return TaxCalculationsHelper.getPercentagePart(getPrice(), new BigDecimal("23.5"));
    }

    public int getItemId() {
        return itemId;
    }

    public int getCustomerId() {
        return customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return itemId == order.getItemId() && customerId == order.getCustomerId();

    }

    @Override
    public int hashCode() {
        int result = itemId;
        result = 31 * result + customerId;

        return result;
    }

    // this should be review with client - it looks reasonable to queued priority orders as FIFO
    public int compareTo(Order o) {
        if (o instanceof PriorityOrder) {
            return 1;
        } else {
            return 0;
        }
    }
}
