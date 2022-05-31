package com.example.pancakem.services.impl;

import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.models.Discount;
import com.example.pancakem.models.Ingredient;
import com.example.pancakem.repositories.DiscountsEntityRepository;
import com.example.pancakem.services.DiscountsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DiscountsServiceImpl implements DiscountsService {

    private final ModelMapper modelMapper;
    private final DiscountsEntityRepository discountsEntityRepository;

    public DiscountsServiceImpl(ModelMapper modelMapper, DiscountsEntityRepository discountsEntityRepository) {
        this.modelMapper = modelMapper;
        this.discountsEntityRepository = discountsEntityRepository;
    }

    @Override
    public Discount findById(Integer id) throws NotFoundException {
        return modelMapper.map(discountsEntityRepository.findById(id).orElseThrow(NotFoundException::new), Discount.class);
    }
}
