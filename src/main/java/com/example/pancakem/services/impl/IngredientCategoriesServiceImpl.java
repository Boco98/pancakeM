package com.example.pancakem.services.impl;

import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.models.IngredientCategory;
import com.example.pancakem.models.SingleIngredientCategory;
import com.example.pancakem.repositories.IngredientCategoriesEntityRepository;
import com.example.pancakem.services.IngredientCategoriesService;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientCategoriesServiceImpl implements IngredientCategoriesService {

    private final ModelMapper modelMapper;
    private final IngredientCategoriesEntityRepository repository;

    public IngredientCategoriesServiceImpl(IngredientCategoriesEntityRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<IngredientCategory> findAll() {
        return repository.findAll().stream().map(l->modelMapper.map(l, IngredientCategory.class)).collect(Collectors.toList());
    }

    @Override
    public SingleIngredientCategory findById(Integer id) throws NotFoundException {
        return modelMapper.map(repository.findById(id).orElseThrow(NotFoundException::new),SingleIngredientCategory.class);
    }
}
