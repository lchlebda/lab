package com.getbase.recruit.orders;

import com.getbase.recruit.SeriousEnterpriseEventBus;
import com.getbase.recruit.SeriousEnterpriseEventBusLookup;
import com.getbase.recruit.TaxCalculationsHelper;

import java.math.BigDecimal;

public abstract class AbstractOrderHelper {

    protected SeriousEnterpriseEventBus seeb = SeriousEnterpriseEventBusLookup.seeb;

    protected void process(boolean isNotStandard) {
        if (!isNotStandard) {
            return;
        }

        processNotStandard();
    }

    protected BigDecimal getPrice(BigDecimal price, boolean isNotStandard) {
        if (!isNotStandard) {
            return price;
        }

        return changePrice(price);
    }

    abstract void processNotStandard();
    abstract BigDecimal changePrice(BigDecimal price);

}
