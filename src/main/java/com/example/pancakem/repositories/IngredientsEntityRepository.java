package com.example.pancakem.repositories;

import com.example.pancakem.models.entities.IngredientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsEntityRepository extends JpaRepository<IngredientsEntity, Integer> {
}
