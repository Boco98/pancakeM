package com.example.pancakem.services.impl;

import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.models.Pancake;
import com.example.pancakem.models.SinglePancake;
import com.example.pancakem.repositories.PancakesEntityRepository;
import com.example.pancakem.services.PancakesService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
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


    @Override
    public List<Pancake> findAll() {
        return pancakesEntityRepository.findAll().stream().map(l->modelMapper.map(l, Pancake.class)).collect(Collectors.toList());
    }

    @Override
    public SinglePancake findById(Integer id) throws NotFoundException {
        return modelMapper.map(pancakesEntityRepository.findById(id).orElseThrow(NotFoundException::new), SinglePancake.class);
    }
}
