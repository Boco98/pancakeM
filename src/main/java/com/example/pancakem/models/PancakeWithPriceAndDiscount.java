package com.example.pancakem.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
@EqualsAndHashCode(callSuper = true)
@Data
public class PancakeWithPriceAndDiscount extends PancakeWithPrice{
    BigDecimal priceWithDiscount;

    public PancakeWithPriceAndDiscount(Pancake pancake, BigDecimal price, BigDecimal priceWithDiscount){
        super(pancake, price);
        this.priceWithDiscount = priceWithDiscount;
    }
}
