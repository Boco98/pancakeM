package com.example.pancakem.services;

import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.models.Ingredient;

import java.util.List;

public interface IngredientsService {
    List<Ingredient> findAll();

    Ingredient findById(Integer id) throws NotFoundException;
}
