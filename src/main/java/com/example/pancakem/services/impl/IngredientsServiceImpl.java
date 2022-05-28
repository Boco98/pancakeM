package com.example.pancakem.services.impl;

import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.models.Ingredient;
import com.example.pancakem.repositories.IngredientsEntityRepository;
import com.example.pancakem.services.IngredientsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientsServiceImpl implements IngredientsService {

    private final ModelMapper modelMapper;

    public IngredientsServiceImpl(ModelMapper modelMapper, IngredientsEntityRepository ingredientsEntityRepository) {
        this.modelMapper = modelMapper;
        this.ingredientsEntityRepository= ingredientsEntityRepository;
    }

    private final IngredientsEntityRepository ingredientsEntityRepository;
    @Override
    public List<Ingredient> findAll() {
        return ingredientsEntityRepository.findAll().stream().map(l->modelMapper.map(l, Ingredient.class)).collect(Collectors.toList());
    }

    @Override
    public Ingredient findById(Integer id)  throws NotFoundException {
        return modelMapper.map(ingredientsEntityRepository.findById(id).orElseThrow(NotFoundException::new),Ingredient.class);
    }

    @Override
    public List<Ingredient> getAllIngredientsByCategoryId(Integer id) {
        return ingredientsEntityRepository.getAllByIngredientCategories_Id(id).stream().map(l->modelMapper.map(l, Ingredient.class)).collect(Collectors.toList());
    }
}
