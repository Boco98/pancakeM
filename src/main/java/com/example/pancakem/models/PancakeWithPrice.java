package com.example.pancakem.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PancakeWithPrice {
    private Integer id;
    private String name;
    private BigDecimal price;
    public PancakeWithPrice(Pancake pancake, BigDecimal price){
        this.id = pancake.getId();
        this.name = pancake.getName();
        this.price = price;
    }
    public PancakeWithPrice(){

    }
}

