package com.example.pancakem.controllers;

import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.models.Ingredient;
import com.example.pancakem.models.IngredientRequest;
import com.example.pancakem.models.entities.IngredientsEntity;
import com.example.pancakem.repositories.IngredientsEntityRepository;
import com.example.pancakem.services.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        ingredientsService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient insert(@RequestBody IngredientRequest ingredientRequest) throws NotFoundException {
        return ingredientsService.insert(ingredientRequest);
    }

    @PutMapping("/{id}")
    public Ingredient update(@PathVariable Integer id, @RequestBody IngredientRequest ingredientRequest) throws NotFoundException {
        return ingredientsService.update(id,ingredientRequest);
    }
}
