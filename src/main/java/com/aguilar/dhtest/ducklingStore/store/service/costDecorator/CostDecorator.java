package com.aguilar.dhtest.ducklingStore.store.service.costDecorator;

public abstract class CostDecorator implements CostI {

    protected CostI cost;

    public CostDecorator(CostI auxCost) {
        this.cost = auxCost;
    }

    public double getTotalPrice() {
        return cost.getTotalPrice();
    }

    public double getQuantity() {
        return cost.getQuantity();
    }

    public double getExtraPrice() {
        return cost.getExtraPrice();
    }

    public String getDetail(){
        return cost.getDetail();
    }
}
