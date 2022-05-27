package com.example.pancakem.models;

import com.example.pancakem.models.Ingredient;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SingleIngredientCategory extends IngredientCategory {
    List<Ingredient> ingredients;
}
