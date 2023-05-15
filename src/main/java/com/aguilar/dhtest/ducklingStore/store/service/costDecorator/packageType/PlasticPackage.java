package com.aguilar.dhtest.ducklingStore.store.service.costDecorator.packageType;

import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.CostDecorator;
import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.CostI;

public class PlasticPackage extends CostDecorator {

    public PlasticPackage(CostI auxCost) {
        super(auxCost);
    }

    public double getTotalPrice() {
        return cost.getTotalPrice() + this.getExtraPrice();
    }

    public double getQuantity() {
        return cost.getQuantity();
    }

    public double getExtraPrice() {
        return cost.getTotalPrice() * 0.1;
    }

    public String getDetail(){
        return cost.getDetail() + "Paquete de plastico: " + this.getExtraPrice() + "|";
    }

}
