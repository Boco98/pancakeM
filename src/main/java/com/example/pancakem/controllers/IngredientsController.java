package com.example.pancakem.controllers;

import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.models.Ingredient;
import com.example.pancakem.models.entities.IngredientsEntity;
import com.example.pancakem.repositories.IngredientsEntityRepository;
import com.example.pancakem.services.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {

    private final IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping
    List<Ingredient> findAll(){
        return ingredientsService.findAll();
    }

    @GetMapping("/{id}")
    public Ingredient findById(@PathVariable Integer id) throws NotFoundException {
        return ingredientsService.findById(id);
    }
}
