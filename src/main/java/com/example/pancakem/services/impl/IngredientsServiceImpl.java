package com.example.pancakem.services.impl;

import com.example.pancakem.exceptions.ConflictException;
import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.models.Ingredient;
import com.example.pancakem.models.IngredientRequest;
import com.example.pancakem.models.entities.IngredientsEntity;
import com.example.pancakem.repositories.IngredientsEntityRepository;
import com.example.pancakem.services.IngredientsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class IngredientsServiceImpl implements IngredientsService {

    private final ModelMapper modelMapper;

    public IngredientsServiceImpl(ModelMapper modelMapper, IngredientsEntityRepository ingredientsEntityRepository) {
        this.modelMapper = modelMapper;
        this.ingredientsEntityRepository= ingredientsEntityRepository;
    }

    private final IngredientsEntityRepository ingredientsEntityRepository;
    @PersistenceContext
    private EntityManager entityManager;
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

    @Override
    public void delete(Integer id) {
        ingredientsEntityRepository.deleteById(id);
    }

    @Override
    public Ingredient insert(IngredientRequest ingredientRequest) throws NotFoundException {
        IngredientsEntity ingredientsEntity=modelMapper.map(ingredientRequest, IngredientsEntity.class);
        ingredientsEntity.setId(null);
        if(ingredientsEntityRepository.existsByName(ingredientsEntity.getName()))
            throw new ConflictException();
        ingredientsEntity= ingredientsEntityRepository.saveAndFlush(ingredientsEntity);
        entityManager.refresh(ingredientsEntity);
        return findById(ingredientsEntity.getId());
    }
    @Override
    public Ingredient update(Integer id, IngredientRequest ingredientRequest) throws NotFoundException {
        IngredientsEntity ingredientsEntity=modelMapper.map(ingredientRequest, IngredientsEntity.class);
        ingredientsEntity.setId(id);
        /*if(ingredientsEntityRepository.existsByName(ingredientsEntity.getName()))
            throw new ConflictException();*/
        ingredientsEntity= ingredientsEntityRepository.saveAndFlush(ingredientsEntity);
        entityManager.refresh(ingredientsEntity);
        return findById(ingredientsEntity.getId());
    }
}
