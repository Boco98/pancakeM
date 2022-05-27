package com.example.pancakem.controllers;

import com.example.pancakem.models.entities.IngredientsEntity;
import com.example.pancakem.repositories.IngredientsEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {

    private final IngredientsEntityRepository repository;

    public IngredientsController(IngredientsEntityRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<IngredientsEntity> findAll(){
        return repository.findAll();
    }
}
