package com.aguilar.dhtest.ducklingStore.store.dto;

import com.aguilar.dhtest.ducklingStore.common.utils.ValueList;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StoreOrderDTO {
    private String color;
    private String size;
    private Integer ducklingQuantity;
    private String destinationCountry;
    private String shippingMethod;

    public List<String> getInvalidFields() {
        List<String> fields = new ArrayList<>();

        if (this.size == null || !ValueList.validSizes.contains(this.size.toUpperCase().trim())) {
            fields.add("Tamaño");
        }
        if (this.color == null || !ValueList.validColors.contains(this.color.toUpperCase().trim())) {
            fields.add("Color");
        }
        if (this.shippingMethod == null || !ValueList.validShippingMethods.contains(this.shippingMethod.toUpperCase().trim())) {
            fields.add("Medio de envío");
        }
        if (this.ducklingQuantity <= 0) {
            fields.add("Cantidad");
        }
        if (this.destinationCountry == null || this.destinationCountry.isEmpty()) {
            fields.add("Destino");
        }
        return fields;
    }
}
