package com.getbase.recruit.orders;

import com.getbase.recruit.TaxCalculationsHelper;

import java.math.BigDecimal;

public class DiscountedOrderHelper extends AbstractOrderHelper{

    @Override
    void processNotStandard() {
        seeb.sendEvent("Run fraud detection and revenue integrity check");
    }

    @Override
    BigDecimal changePrice(BigDecimal price) {
        return TaxCalculationsHelper.subtractPercentage(price, new BigDecimal("11"));
    }

}
