package com.aguilar.dhtest.ducklingStore.store.service.costDecorator.packageType;

import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.CostDecorator;
import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.CostI;

public class WoodPackage extends CostDecorator {

    public WoodPackage(CostI auxCost) {
        super(auxCost);
    }

    public double getTotalPrice() {
        return cost.getTotalPrice() + this.getExtraPrice();
    }

    public double getQuantity() {
        return cost.getQuantity();
    }

    public double getExtraPrice() {
        return cost.getTotalPrice() * 0.05;
    }

    public String getDetail(){
        return cost.getDetail() + "Paquete de madera: " + this.getExtraPrice() + "|";
    }

}
