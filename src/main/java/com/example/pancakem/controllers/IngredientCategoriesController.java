package com.example.pancakem.controllers;

import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.models.IngredientCategory;
import com.example.pancakem.models.SingleIngredientCategory;
import com.example.pancakem.models.entities.IngredientCategoriesEntity;
import com.example.pancakem.repositories.IngredientCategoriesEntityRepository;
import com.example.pancakem.services.IngredientCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ingredient_categories")
public class IngredientCategoriesController {


    private final IngredientCategoriesService ingredientCategoriesService;

    public IngredientCategoriesController(IngredientCategoriesService ingredientCategoriesService) {
        this.ingredientCategoriesService = ingredientCategoriesService;
    }


    @GetMapping
    public List<IngredientCategory> findAll(){
        return  ingredientCategoriesService.findAll();
    }

    @RequestMapping("/{id}")
    public SingleIngredientCategory findById(@PathVariable Integer id) throws NotFoundException {
        return ingredientCategoriesService.findById(id);
    }
}
