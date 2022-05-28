package com.example.pancakem.services;

import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.models.Ingredient;
import com.example.pancakem.models.IngredientRequest;

import java.util.List;

public interface IngredientsService {
    List<Ingredient> findAll();

    Ingredient findById(Integer id) throws NotFoundException;

    List<Ingredient> getAllIngredientsByCategoryId(Integer id);

    void delete(Integer id);
    Ingredient insert(IngredientRequest ingredientRequest) throws NotFoundException;
    Ingredient update(Integer id, IngredientRequest ingredientRequest) throws NotFoundException;
}
