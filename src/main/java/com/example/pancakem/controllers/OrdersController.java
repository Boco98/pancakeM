package com.example.pancakem.controllers;

import com.example.pancakem.exceptions.ConflictException;
import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.models.*;
import com.example.pancakem.services.OrdersService;
import com.example.pancakem.services.PancakesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        ordersService.delete(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order insert(@RequestBody OrderRequest orderRequest) throws NotFoundException, ConflictException {
        return ordersService.insert(orderRequest);
    }

    @PutMapping("/{id}")
    public Order update(@PathVariable Integer id, @RequestBody OrderRequest orderRequest) throws  NotFoundException, ConflictException{
        return ordersService.update(id, orderRequest);
    }


}
