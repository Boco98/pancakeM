package com.example.pancakem.controllers;

import com.example.pancakem.models.entities.PancakesEntity;
import com.example.pancakem.repositories.PancakesEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pancakes")
public class PancakesController {

    private final PancakesEntityRepository repository;

    public PancakesController(PancakesEntityRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<PancakesEntity> findAll(){
        return repository.findAll();
    }
}
