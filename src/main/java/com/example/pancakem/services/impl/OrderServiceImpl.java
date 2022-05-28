package com.example.pancakem.services.impl;

import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.models.Order;
import com.example.pancakem.models.Pancake;
import com.example.pancakem.models.SingleOrder;
import com.example.pancakem.models.SinglePancake;
import com.example.pancakem.repositories.OrdersEntityRepository;
import com.example.pancakem.services.OrdersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrdersService {

    private final ModelMapper modelMapper;
    private final OrdersEntityRepository ordersEntityRepository;

    public OrderServiceImpl(ModelMapper modelMapper, OrdersEntityRepository ordersEntityRepository) {
        this.modelMapper = modelMapper;
        this.ordersEntityRepository = ordersEntityRepository;
    }

    @Override
    public List<Order> findAll() {
        return ordersEntityRepository.findAll().stream().map(l->modelMapper.map(l, Order.class)).collect(Collectors.toList());
    }

    @Override
    public SingleOrder findById(Integer id) throws NotFoundException {
        return modelMapper.map(ordersEntityRepository.findById(id).orElseThrow(NotFoundException::new), SingleOrder.class);
    }
}
