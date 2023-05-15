package com.aguilar.dhtest.ducklingStore.store.service.costDecorator.shippingMethod;

import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.CostDecorator;
import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.CostI;

public class Land extends CostDecorator {

    public Land(CostI auxCost) {
        super(auxCost);
    }

    public double getTotalPrice() {
        return cost.getTotalPrice() + this.getExtraPrice();
    }

    public double getQuantity() {
        return cost.getQuantity();
    }

    public double getExtraPrice() {
        return 10 * cost.getQuantity();
    }

    public String getDetail(){
        return cost.getDetail() + "Envio por tiera: " + this.getExtraPrice() + "|";
    }
}
