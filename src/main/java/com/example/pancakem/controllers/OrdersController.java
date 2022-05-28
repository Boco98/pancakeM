package com.example.pancakem.controllers;

import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.models.Order;
import com.example.pancakem.models.SingleOrder;
import com.example.pancakem.services.OrdersService;
import com.example.pancakem.services.PancakesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;
    private final PancakesService pancakesService;

    public OrdersController(OrdersService ordersService, PancakesService pancakesService) {
        this.ordersService = ordersService;
        this.pancakesService = pancakesService;
    }

    @GetMapping
    List<Order> findAll(){
        return ordersService.findAll();
    }

    @GetMapping("/{id}")
    public SingleOrder findById(@PathVariable Integer id) throws NotFoundException {
        return ordersService.findById(id);
    }
}
