package com.example.pancakem.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IngredientRequest {
    private String name;
    private BigDecimal price;
    private Boolean healthyIngredient;
    private Integer ingredientCategoriesId;
}
