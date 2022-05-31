package com.example.pancakem.services;

import com.example.pancakem.exceptions.ConflictException;
import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.models.Ingredient;
import com.example.pancakem.models.IngredientRequest;

import javax.persistence.EntityManager;
import java.util.List;

public interface IngredientsService {
    List<Ingredient> findAll();

    Ingredient findById(Integer id) throws NotFoundException;

    List<Ingredient> getAllIngredientsByCategoryId(Integer id)throws NotFoundException;

    void delete(Integer id)throws NotFoundException;
    EntityManager getManager();
    Ingredient insert(IngredientRequest ingredientRequest) throws NotFoundException, ConflictException;
    Ingredient update(Integer id, IngredientRequest ingredientRequest) throws NotFoundException;
}
