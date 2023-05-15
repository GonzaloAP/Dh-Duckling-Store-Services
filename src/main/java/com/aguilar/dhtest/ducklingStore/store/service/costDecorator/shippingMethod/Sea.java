package com.aguilar.dhtest.ducklingStore.store.service.costDecorator.shippingMethod;

import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.CostDecorator;
import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.CostI;

public class Sea extends CostDecorator {

    public Sea(CostI auxCost) {
        super(auxCost);
    }

    public double getTotalPrice() {
        return cost.getTotalPrice() + this.getExtraPrice();
    }

    public double getQuantity() {
        return cost.getQuantity();
    }

    public double getExtraPrice() {
        return 400;
    }

    public String getDetail(){
        return cost.getDetail() + "Envio por mar: " + this.getExtraPrice() + "|";
    }
}
