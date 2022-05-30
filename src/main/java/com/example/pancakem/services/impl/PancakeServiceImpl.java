package com.example.pancakem.services.impl;

import com.example.pancakem.exceptions.ConflictException;
import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.models.*;
import com.example.pancakem.models.entities.IngredientsEntity;
import com.example.pancakem.models.entities.PancakesEntity;
import com.example.pancakem.repositories.IngredientsEntityRepository;
import com.example.pancakem.repositories.PancakesEntityRepository;
import com.example.pancakem.services.IngredientsService;
import com.example.pancakem.services.PancakesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public void delete(Integer id) {
        pancakesEntityRepository.deleteById(id);
    }


    @Transactional
    @Override
    public Pancake insert(PancakeRequest pancakeRequest) throws NotFoundException, ConflictException {

        PancakesEntity pancakesEntity = modelMapper.map(pancakeRequest, PancakesEntity.class);
        pancakesEntity.setId(null);
      /*  pancakeRequest.getIngredients().stream().map(l->{
                    IngredientsEntity ingredientsEntity =modelMapper.map(l, IngredientsEntity.class);
                    ingredientsEntity.getPancakes().add(pancakesEntity);
                }
        );*/
        /*for (IngredientsEntity  x: pancakesEntity.getIngredients()
             ) {
            Ingredient a = ingredientsService.findById(x.getId());
            x = modelMapper.map(a, IngredientsEntity.class);
            x = ingredientsEntityRepository.saveAndFlush(x);
        }*/


        //pancakesEntity.setIngredients(pancakeRequest.getIngredients().stream().map(l->modelMapper.map(l, IngredientsEntity.class)).collect(Collectors.toList()));

        //pancakesEntityRepository.findAll().stream().map(l->modelMapper.map(l, Pancake.class)).collect(Collectors.toList());

        if(pancakesEntityRepository.existsByName(pancakesEntity.getName()))
            throw new ConflictException();
        //System.out.println("###"+pancakeRequest.getIngredients().toArray().length);
        //System.out.println("######"+pancakesEntity.getIngredients().toArray().length);
        pancakesEntity = pancakesEntityRepository.saveAndFlush(pancakesEntity);

        entityManager.refresh(pancakesEntity);
        return findById(pancakesEntity.getId());
    }

    @Transactional
    @Override
    public Pancake update(Integer id, PancakeRequest pancakeRequest) throws NotFoundException, ConflictException {
        PancakesEntity pancakesEntity = modelMapper.map(pancakeRequest, PancakesEntity.class);
        pancakesEntity.setId(id);
        /*
        if(pancakesEntityRepository.existsByName(pancakesEntity.getName()))
            throw new ConflictException();*/
        pancakesEntity = pancakesEntityRepository.saveAndFlush(pancakesEntity);
        entityManager.refresh(pancakesEntity);
        return findById(pancakesEntity.getId());
    }


}
