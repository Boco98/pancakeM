package com.example.pancakem.models;

import com.example.pancakem.models.entities.PancakesEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class IngredientMM {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Boolean healthyIngredient;
    private Integer ingredientCategoriesId;
    List<PancakesEntity> pancakes;
}
