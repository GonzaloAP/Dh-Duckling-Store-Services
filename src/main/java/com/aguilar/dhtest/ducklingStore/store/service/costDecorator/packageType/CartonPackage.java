package com.aguilar.dhtest.ducklingStore.store.service.costDecorator.packageType;

import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.CostDecorator;
import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.CostI;

public class CartonPackage extends CostDecorator {


    public CartonPackage(CostI auxCost) {
        super(auxCost);
    }

    public double getTotalPrice() {
        return cost.getTotalPrice() - this.getExtraPrice();
    }

    public double getQuantity() {
        return cost.getQuantity();
    }

    public double getExtraPrice() {
        return cost.getTotalPrice() * 0.01;
    }

    public String getDetail(){
        double auxExtraPrice = this.getExtraPrice() * -1;
        return cost.getDetail() + "Paquete de carton: " + auxExtraPrice + "|";
    }

}
