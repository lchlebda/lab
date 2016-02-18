package com.getbase.recruit.orders;

import com.getbase.recruit.SeriousEnterpriseEventBus;
import com.getbase.recruit.SeriousEnterpriseEventBusLookup;
import com.getbase.recruit.TaxCalculationsHelper;

import java.math.BigDecimal;

public class DiscountedOrder extends AbstractOrder {

    public DiscountedOrder(Order order) {
        super(order);
    }

    @Override
    public BigDecimal getPrice() {
        return TaxCalculationsHelper.subtractPercentage(super.getPrice(), new BigDecimal("11"));
    }

    @Override
    public void process() {
        SeriousEnterpriseEventBus seeb = SeriousEnterpriseEventBusLookup.seeb;
        seeb.sendEvent("Order processing started");

        seeb.sendEvent("Run fraud detection and revenue integrity check");

        seeb.sendEvent("Initiate shipment");
        seeb.sendEvent("Order processing finished");
    }
}
