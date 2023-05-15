package com.aguilar.dhtest.ducklingStore.warehouse.dto;

import com.aguilar.dhtest.ducklingStore.common.utils.ValueList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DucklingDTO implements Serializable{

    private Long id;

    private String color;

    private String size;

    private Double price;

    private Integer quantity;


    @JsonIgnore
    public List<String> getInvalidFields() {
        List<String> fields = new ArrayList<>();

        if (this.color == null || !ValueList.validColors.contains(this.color.toUpperCase().trim())) {
            fields.add("Color");
        }
        if (this.size == null || !ValueList.validSizes.contains(this.size.toUpperCase().trim())) {
            fields.add("Tamaño");
        }
        if (this.price <= 0) {
            fields.add("Precio");
        }
        if (this.quantity <= 0) {
            fields.add("Cantidad");
        }
        return fields;
    }

    @JsonIgnore
    public List<String> getInvalidFieldsWithId() {
        List<String> fields = this.getInvalidFields();
        if (this.id <= 0) {
            fields.add("Id");
        }

        return fields;
    }

}
