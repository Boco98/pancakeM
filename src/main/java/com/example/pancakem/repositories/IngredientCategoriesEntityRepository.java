package com.example.pancakem.repositories;

import com.example.pancakem.models.entities.IngredientCategoriesEntity;
import com.example.pancakem.models.entities.IngredientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientCategoriesEntityRepository extends JpaRepository<IngredientCategoriesEntity, Integer> {
}
