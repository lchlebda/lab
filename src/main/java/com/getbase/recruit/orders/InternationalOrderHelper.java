package com.getbase.recruit.orders;

import com.getbase.recruit.TaxCalculationsHelper;

import java.math.BigDecimal;

public class InternationalOrderHelper extends AbstractOrderHelper {

    @Override
    void processNotStandard() {
        seeb.sendEvent("Dispatch translated order confirmation email");
    }

    @Override
    BigDecimal changePrice(BigDecimal price) {
        return price;
    }

    BigDecimal getTax(BigDecimal price) {
        return TaxCalculationsHelper.getPercentagePart(price, new BigDecimal("15.0"));
    }
}
