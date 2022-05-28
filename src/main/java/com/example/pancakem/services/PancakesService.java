package com.example.pancakem.services;

import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.models.Pancake;
import com.example.pancakem.models.SinglePancake;

import java.util.List;

public interface PancakesService {
    List<Pancake> findAll();
    SinglePancake findById(Integer id) throws NotFoundException;

}
