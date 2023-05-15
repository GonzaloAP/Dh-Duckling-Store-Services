package com.aguilar.dhtest.ducklingStore.store.service.costDecorator.shippingMethod;

import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.CostDecorator;
import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.CostI;

public class AirPlane extends CostDecorator {

    public AirPlane(CostI auxCost) {
        super(auxCost);
    }

    public double getTotalPrice() {
        double tempCost = cost.getTotalPrice() + this.getExtraPrice();
        if (cost.getQuantity() > 1000) {
            tempCost = tempCost - (tempCost * 0.15);
        }

        return tempCost;
    }

    public double getQuantity() {
        return cost.getQuantity();
    }

    public double getExtraPrice() {
        return 30 * cost.getQuantity();
    }

    public String getDetail(){
        return cost.getDetail() + "Envio por avion: " + this.getExtraPrice() + "|";
    }
}
