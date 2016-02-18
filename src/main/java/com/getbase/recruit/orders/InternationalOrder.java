package com.getbase.recruit.orders;

import com.getbase.recruit.SeriousEnterpriseEventBus;
import com.getbase.recruit.SeriousEnterpriseEventBusLookup;
import com.getbase.recruit.TaxCalculationsHelper;

import java.math.BigDecimal;

public class InternationalOrder extends AbstractOrder {

    public InternationalOrder(Order order) {
        super(order);
    }


    @Override
    public void process() {
        SeriousEnterpriseEventBus seeb = SeriousEnterpriseEventBusLookup.seeb;
        seeb.sendEvent("Order processing started");

        seeb.sendEvent("Dispatch translated order confirmation email");

        seeb.sendEvent("Initiate shipment");
        seeb.sendEvent("Order processing finished");
    }

    @Override
    public BigDecimal getTax() {
        BigDecimal commonTax = super.getTax();
        BigDecimal internationalTax = TaxCalculationsHelper.getPercentagePart(getPrice(), new BigDecimal("15.0"));

        return commonTax.add(internationalTax);
    }
}
