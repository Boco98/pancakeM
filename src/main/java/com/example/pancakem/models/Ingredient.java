package com.example.pancakem.models;

import com.example.pancakem.models.entities.IngredientCategoriesEntity;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.math.BigDecimal;

@Data
public class Ingredient {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Boolean healthyIngredient;
    private String ingredient_categoriesName;
}
