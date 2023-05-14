package com.aguilar.dhtest.ducklingStore.warehouse.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "Duckling")
public class DucklingEntity extends BaseEntity {

    @Column(name = "color", nullable = false, length = 15)
    private String color;

    @Column(name = "size", nullable = false, length = 10)
    private String size;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "isDeleted", nullable = false)
    private Boolean isDeleted = false;
}
