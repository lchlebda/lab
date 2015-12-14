package com.getbase.recruit.orders;

import com.getbase.recruit.TaxCalculationsHelper;

import java.math.BigDecimal;

public class PriorityOrderHelper extends AbstractOrderHelper {

    @Override
    void processNotStandard() {
        seeb.sendEvent("*** This is priority order, hurry up! ***");
    }

    @Override
    BigDecimal changePrice(BigDecimal price) {
        return TaxCalculationsHelper.addPercentage(price,new BigDecimal("1.5"));

    }

}
