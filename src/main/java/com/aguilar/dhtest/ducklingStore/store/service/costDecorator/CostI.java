package com.aguilar.dhtest.ducklingStore.store.service.costDecorator;

public interface CostI {
    double getTotalPrice();
    double getQuantity();
    double getExtraPrice();

    String getDetail();

}
