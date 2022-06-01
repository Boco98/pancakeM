package com.example.pancakem.services;

import com.example.pancakem.exceptions.ConflictException;
import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.exceptions.PancakeException;
import com.example.pancakem.models.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;

public interface OrdersService {
    List<Order> findAll();
    SingleOrder findById(Integer id) throws NotFoundException;

    SingleOrderWithPrice findOrderPriceById(Integer id) throws  NotFoundException;
    SingleOrderWithPriceAndPancakesPrices findByIdWithPriceAndPancakePrices( Integer id) throws  NotFoundException;
    void delete(Integer id);
    Order insert(OrderRequest orderRequest) throws NotFoundException, ConflictException, PancakeException;
    Order update(Integer id, OrderRequest orderRequest) throws NotFoundException, ConflictException;
}
