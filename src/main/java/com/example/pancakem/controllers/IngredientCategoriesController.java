package com.example.pancakem.controllers;

import com.example.pancakem.models.entities.IngredientCategoriesEntity;
import com.example.pancakem.repositories.IngredientCategoriesEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ingredient_categories")
public class IngredientCategoriesController {

    private final IngredientCategoriesEntityRepository repository;

    public IngredientCategoriesController(IngredientCategoriesEntityRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<IngredientCategoriesEntity> findAll(){
        return  repository.findAll();
    }
}
