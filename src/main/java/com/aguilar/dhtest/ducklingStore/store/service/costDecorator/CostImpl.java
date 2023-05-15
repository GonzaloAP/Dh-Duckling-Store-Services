package com.aguilar.dhtest.ducklingStore.store.service.costDecorator;

public class CostImpl implements CostI {

    public double price;
    public double quantity;

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public double getTotalPrice() {
        return this.price + this.getExtraPrice();
    }

    @Override
    public double getQuantity() {
        return this.quantity;
    }

    @Override
    public double getExtraPrice() {
        if (this.quantity > 100) {
            return price * 0.20;
        }
        return 0;
    }

    @Override
    public String getDetail() {
        if (this.getExtraPrice() > 0) {
            double auxExtraPrice = this.getExtraPrice() * -1;
            return "Envio mayor a 100 unidades: " + auxExtraPrice + "|";
        }
        return "";
    }
}
