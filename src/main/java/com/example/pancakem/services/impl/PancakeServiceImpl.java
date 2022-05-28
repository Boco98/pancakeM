package com.example.pancakem.services.impl;

import com.example.pancakem.exceptions.ConflictException;
import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.models.Pancake;
import com.example.pancakem.models.PancakeRequest;
import com.example.pancakem.models.SinglePancake;
import com.example.pancakem.models.entities.PancakesEntity;
import com.example.pancakem.repositories.PancakesEntityRepository;
import com.example.pancakem.services.PancakesService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PancakeServiceImpl implements PancakesService {

    private final ModelMapper modelMapper;

    public PancakeServiceImpl(ModelMapper modelMapper, PancakesEntityRepository pancakesEntityRepository) {
        this.modelMapper = modelMapper;
        this.pancakesEntityRepository = pancakesEntityRepository;
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
