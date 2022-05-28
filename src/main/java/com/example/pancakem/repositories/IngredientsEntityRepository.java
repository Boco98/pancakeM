package com.example.pancakem.repositories;

import com.example.pancakem.models.entities.IngredientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientsEntityRepository extends JpaRepository<IngredientsEntity, Integer> {
    List<IngredientsEntity> getAllByIngredientCategories_Id(Integer id);
}
