package com.aguilar.dhtest.ducklingStore.store.service.costDecorator.destinationCountry;

import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.CostDecorator;
import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.CostI;

public class BoliviaCountry extends CostDecorator {

    public BoliviaCountry(CostI auxCost) {
        super(auxCost);
    }

    public double getTotalPrice() {
        return cost.getTotalPrice() + this.getExtraPrice();
    }

    public double getQuantity() {
        return cost.getQuantity();
    }

    public double getExtraPrice() {
        return cost.getTotalPrice() * 0.13;
    }

    public String getDetail(){
        return cost.getDetail() + "Destino Bolivia: " + this.getExtraPrice() + "|";
    }
}
