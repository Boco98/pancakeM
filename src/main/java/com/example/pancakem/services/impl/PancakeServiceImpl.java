package com.example.pancakem.services.impl;

import com.example.pancakem.exceptions.ConflictException;
import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.models.*;
import com.example.pancakem.models.entities.PancakesEntity;
import com.example.pancakem.repositories.IngredientsEntityRepository;
import com.example.pancakem.repositories.PancakesEntityRepository;
import com.example.pancakem.services.IngredientsService;
import com.example.pancakem.services.PancakesService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PancakeServiceImpl implements PancakesService {

    private final ModelMapper modelMapper;

    private final IngredientsEntityRepository ingredientsEntityRepository;


    private final IngredientsService ingredientsService;
    public PancakeServiceImpl(ModelMapper modelMapper, PancakesEntityRepository pancakesEntityRepository, IngredientsService ingredientsService, IngredientsEntityRepository ingredientsEntityRepository) {
        this.modelMapper = modelMapper;
        this.pancakesEntityRepository = pancakesEntityRepository;
        this.ingredientsService = ingredientsService;
        this.ingredientsEntityRepository = ingredientsEntityRepository;
    }
    private final PancakesEntityRepository pancakesEntityRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Pancake> findAll() {
        return pancakesEntityRepository.findAll().stream().map(l->modelMapper.map(l, Pancake.class)).collect(Collectors.toList());
    }

    @Override
    public SinglePancake findById(Integer id) throws NotFoundException {
        return modelMapper.map(pancakesEntityRepository.findById(id).orElseThrow(NotFoundException::new), SinglePancake.class);
    }

    @Override
    public List<Pancake> findCorrectPancake() {
        List<PancakesEntity> filteredList = pancakesEntityRepository.findAll().stream().filter(c -> isCorrectPancake(modelMapper.map(c, Pancake.class))).toList();
        return filteredList.stream().map(c -> modelMapper.map(c, Pancake.class)).collect(Collectors.toList());
    }

    @Override
    public List<PancakeWithPrice> getPancakeWithPrice() {
        List<Pancake> pancakeList=findAll();
        return pancakeList.stream().map(c->new PancakeWithPrice(c, calculatePrice(c.getId()))).collect(Collectors.toList());
    }
    public BigDecimal calculatePrice(Integer pancakeId){
        SinglePancake pancake = findById(pancakeId);
        BigDecimal sum = new BigDecimal(0);
        for(Ingredient x : pancake.getIngredients()){
            sum = sum.add(x.getPrice());
        }

        return sum;
    }
    @Override
    public void delete(Integer id)throws NotFoundException {
        pancakesEntityRepository.deleteById(id);
    }


    @Transactional
    @Override
    public Pancake insert(PancakeRequest pancakeRequest) throws NotFoundException, ConflictException {

        PancakesEntity pancakesEntity = modelMapper.map(pancakeRequest, PancakesEntity.class);
        pancakesEntity.setId(null);
        if(pancakesEntityRepository.existsByName(pancakesEntity.getName()))
            throw new ConflictException();
        pancakesEntity = pancakesEntityRepository.saveAndFlush(pancakesEntity);

        entityManager.refresh(pancakesEntity);
        return findById(pancakesEntity.getId());
    }

    @Transactional
    @Override
    public Pancake update(Integer id, PancakeRequest pancakeRequest) throws NotFoundException{
        PancakesEntity pancakesEntity = modelMapper.map(pancakeRequest, PancakesEntity.class);
        pancakesEntity.setId(id);
        pancakesEntity = pancakesEntityRepository.saveAndFlush(pancakesEntity);
        entityManager.refresh(pancakesEntity);
        return findById(pancakesEntity.getId());
    }

    @Override
    public boolean isCorrectPancake(Pancake pancake) {
        SinglePancake pancakeS = findById(pancake.getId());
        List<Ingredient> ingredients = pancakeS.getIngredients();
        if(ingredients==null || ingredients.toArray().length==0)
            return  false;
        int numOfBase = 0;
        boolean hasFilling = false;
        for (Ingredient x: pancakeS.getIngredients()) {
            if (x.getIngredientCategoriesId()==1) numOfBase++;
            else if(x.getIngredientCategoriesId()==2) hasFilling=true;
        }

        return (hasFilling && (numOfBase==1));
    }


}
