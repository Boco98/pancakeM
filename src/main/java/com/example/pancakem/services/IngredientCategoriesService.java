package com.example.pancakem.services;

import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.models.IngredientCategory;
import com.example.pancakem.models.SingleIngredientCategory;

import java.util.List;

public interface IngredientCategoriesService {
    List<IngredientCategory> findAll();
    SingleIngredientCategory findById(Integer id) throws NotFoundException;
}
