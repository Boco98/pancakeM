package com.example.pancakem.models;

import com.example.pancakem.services.DiscountsService;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class SingleOrderWithPrice extends SingleOrder{
    private BigDecimal price;
    private String orderDiscount;
    private BigDecimal priceWithDiscount;

}
