package com.example.pancakem.repositories;

import com.example.pancakem.models.entities.IngredientsEntity;
import com.example.pancakem.models.entities.PancakesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PancakesEntityRepository extends JpaRepository<PancakesEntity, Integer> {

}
