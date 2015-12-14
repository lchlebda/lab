package com.getbase.recruit.orders;

import com.getbase.recruit.SeriousEnterpriseEventBus;
import com.getbase.recruit.SeriousEnterpriseEventBusLookup;
import com.getbase.recruit.TaxCalculationsHelper;

import java.math.BigDecimal;

public class Order implements Comparable<Order> {

    private final int itemId;
    private final int customerId;
    private final BigDecimal price;

    private boolean isDiscounted;
    private boolean isInternational;
    private boolean isPriority;
    private static final DiscountedOrderHelper discountedOrderHelper = new DiscountedOrderHelper();
    private static final InternationalOrderHelper internationalOrderHelper = new InternationalOrderHelper();
    private static final PriorityOrderHelper priorityOrderHelper = new PriorityOrderHelper();

    public Order(int itemId, int customerId, BigDecimal price) {
        this.itemId = itemId;
        this.customerId = customerId;
        this.price = price;
    }

    public Order asDiscounted() {
        isDiscounted = true;
        return this;
    }

    public Order asInternational() {
        isInternational = true;
        return this;
    }

    public Order asPriority() {
        isPriority = true;
        return this;
    }

    public void process() {
        SeriousEnterpriseEventBus seeb = SeriousEnterpriseEventBusLookup.seeb;
        seeb.sendEvent("Order processing started");

        discountedOrderHelper.process(isDiscounted);
        internationalOrderHelper.process(isInternational);
        priorityOrderHelper.process(isPriority);

        seeb.sendEvent("Initiate shipment");
        seeb.sendEvent("Order processing finished");
    }

    public BigDecimal getTotalAmount() {
        return price.add(getTax());
    }

    public int getItemId() {
        return itemId;
    }

    public int getCustomerId() {
        return customerId;
    }

    // this should be review with the client - the order of price change in case of mixed type orders
    public BigDecimal getPrice() {
        BigDecimal standardPrice = price;
        BigDecimal discountedPrice = discountedOrderHelper.getPrice(standardPrice, isDiscounted);
        BigDecimal internationalPrice = internationalOrderHelper.getPrice(discountedPrice, isInternational);

        return priorityOrderHelper.getPrice(internationalPrice, isPriority);
    }

    public BigDecimal getTax() {
        if (isInternational) {
            return internationalOrderHelper.getTax(price);
        } else {
            return TaxCalculationsHelper.getPercentagePart(getPrice(), new BigDecimal("23.5"));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return itemId == order.itemId && customerId == order.customerId;

    }

    @Override
    public int hashCode() {
        int result = itemId;
        result = 31 * result + customerId;

        return result;
    }

    // this should be review with client - it looks reasonable to queued priority orders as FIFO
    public int compareTo(Order o) {
        if (isPriority && !o.isPriority) {
            return 1;
        } else {
            return 0;
        }
    }
}
