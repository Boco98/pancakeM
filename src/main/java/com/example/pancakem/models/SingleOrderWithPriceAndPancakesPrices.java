package com.example.pancakem.models;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
public class SingleOrderWithPriceAndPancakesPrices {
    private Integer id;
    private Timestamp orderDatetime;
    private String description;
    private Integer discountsId;
    private BigDecimal price;
    private String orderDiscountString;
    private BigDecimal priceWithDiscount;
    List<PancakeWithPriceAndDiscount> pancakes;
}
