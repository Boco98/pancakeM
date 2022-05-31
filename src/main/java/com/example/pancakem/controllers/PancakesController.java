package com.example.pancakem.controllers;

import com.example.pancakem.exceptions.ConflictException;
import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.models.Pancake;
import com.example.pancakem.models.PancakeRequest;
import com.example.pancakem.models.PancakesWithPrice;
import com.example.pancakem.models.SinglePancake;
import com.example.pancakem.models.entities.PancakesEntity;
import com.example.pancakem.repositories.PancakesEntityRepository;
import com.example.pancakem.services.IngredientsService;
import com.example.pancakem.services.PancakesService;
import net.bytebuddy.utility.nullability.AlwaysNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins =  "http://localhost:3000/")
@RestController
@RequestMapping("/pancakes")
public class PancakesController {
    private final PancakesService pancakesService;
    private final IngredientsService ingredientsService;

    public PancakesController(PancakesService pancakesService, IngredientsService ingredientsService) {
        this.pancakesService = pancakesService;
        this.ingredientsService = ingredientsService;
    }


    @GetMapping
    List<Pancake> findAll(){
        return pancakesService.findAll();
    }

    @GetMapping("/correctPancakes")
    List<Pancake> findCorrectPancake(){
        return pancakesService.findCorrectPancake();
    }
    @GetMapping("/prices")
    List<PancakesWithPrice>  getPancakesWithPrice(){
        return pancakesService.getPancakesWithPrice();
    }
    @GetMapping("/{id}")
    public SinglePancake findById(@PathVariable Integer id) throws NotFoundException {
        return pancakesService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        pancakesService.delete(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pancake insert(@RequestBody PancakeRequest pancakeRequest) throws NotFoundException, ConflictException{
        return pancakesService.insert(pancakeRequest);
    }

    @PutMapping("/{id}")
    public Pancake update(@PathVariable Integer id, @RequestBody PancakeRequest pancakeRequest) throws  NotFoundException, ConflictException{
        return pancakesService.update(id, pancakeRequest);
    }


}
