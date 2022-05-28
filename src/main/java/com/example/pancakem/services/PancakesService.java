package com.example.pancakem.services;

import com.example.pancakem.exceptions.ConflictException;
import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.models.Pancake;
import com.example.pancakem.models.PancakeRequest;
import com.example.pancakem.models.SinglePancake;

import java.util.List;

public interface PancakesService {
    List<Pancake> findAll();
    SinglePancake findById(Integer id) throws NotFoundException;

    void delete(Integer id);
    Pancake insert(PancakeRequest pancakeRequest) throws NotFoundException, ConflictException;
    Pancake update(Integer id, PancakeRequest pancakeRequest) throws NotFoundException, ConflictException;
}
