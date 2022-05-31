package com.example.pancakem.services;

import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.models.Discount;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DiscountsService {
    Discount findById(Integer id) throws NotFoundException;
}
