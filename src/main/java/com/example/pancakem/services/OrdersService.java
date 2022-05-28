package com.example.pancakem.services;

import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.models.Order;
import com.example.pancakem.models.SingleOrder;

import java.util.List;

public interface OrdersService {
    List<Order> findAll();
    SingleOrder findById(Integer id) throws NotFoundException;
}
