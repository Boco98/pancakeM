package com.example.pancakem.services.impl;

import com.example.pancakem.exceptions.ConflictException;
import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.models.*;
import com.example.pancakem.models.entities.OrdersEntity;
import com.example.pancakem.models.entities.PancakesEntity;
import com.example.pancakem.repositories.OrdersEntityRepository;
import com.example.pancakem.services.OrdersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrdersService {

    private final ModelMapper modelMapper;
    private final OrdersEntityRepository ordersEntityRepository;
    @PersistenceContext
    private EntityManager entityManager;
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
    @Override
    public void delete(Integer id) {
        ordersEntityRepository.deleteById(id);
    }
    @Override
    public Order insert(OrderRequest orderRequest)  throws NotFoundException, ConflictException {
        OrdersEntity ordersEntity = modelMapper.map(orderRequest, OrdersEntity.class);
        ordersEntity.setId(null);
        ordersEntity = ordersEntityRepository.saveAndFlush(ordersEntity);
        entityManager.refresh(ordersEntity);
        return findById(ordersEntity.getId());
    }

    public Order update(Integer id, OrderRequest orderRequest) throws NotFoundException, ConflictException {
        OrdersEntity ordersEntity = modelMapper.map(orderRequest, OrdersEntity.class);
        ordersEntity.setId(id);

        ordersEntity = ordersEntityRepository.saveAndFlush(ordersEntity);
        entityManager.refresh(ordersEntity);
        return findById(ordersEntity.getId());
    }


}
