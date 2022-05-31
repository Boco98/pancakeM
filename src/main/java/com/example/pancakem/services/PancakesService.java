package com.example.pancakem.services;

import com.example.pancakem.exceptions.ConflictException;
import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.models.Pancake;
import com.example.pancakem.models.PancakeRequest;
import com.example.pancakem.models.PancakesWithPrice;
import com.example.pancakem.models.SinglePancake;

import java.math.BigDecimal;
import java.util.List;

public interface PancakesService {
    List<Pancake> findAll();
    SinglePancake findById(Integer id) throws NotFoundException;
    List<Pancake> findCorrectPancake();
    BigDecimal calculatePrice(Integer id);
    List<PancakesWithPrice> getPancakesWithPrice();
    void delete(Integer id) throws NotFoundException;
    Pancake insert(PancakeRequest pancakeRequest) throws NotFoundException, ConflictException;
    Pancake update(Integer id, PancakeRequest pancakeRequest) throws NotFoundException;
    boolean isCorrectPancake(Pancake pancake);

}
