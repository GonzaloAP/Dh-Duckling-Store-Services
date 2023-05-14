package com.aguilar.dhtest.ducklingStore.warehouse.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Set<String> validColors = Stream.of("Rojo", "Verde", "Amarillo", "Negro").collect(Collectors.toSet());
        Set<String> validSizes = Stream.of("XLarge", "Large", "Medium", "Small", "XSmall").collect(Collectors.toSet());

        if (!validColors.contains(this.color)) {
            fields.add("Color");
        }
        if (!validSizes.contains(this.size)) {
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
