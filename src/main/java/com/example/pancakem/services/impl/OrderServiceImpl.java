package com.example.pancakem.services.impl;

import com.example.pancakem.exceptions.ConflictException;
import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.exceptions.PancakeException;
import com.example.pancakem.models.*;
import com.example.pancakem.models.entities.OrdersEntity;
import com.example.pancakem.repositories.OrdersEntityRepository;
import com.example.pancakem.services.OrdersService;
import com.example.pancakem.services.PancakesService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrdersService {

    private final ModelMapper modelMapper;
    private final OrdersEntityRepository ordersEntityRepository;


    private final PancakesService pancakesService;
    @PersistenceContext
    private EntityManager entityManager;
    public OrderServiceImpl(ModelMapper modelMapper, OrdersEntityRepository ordersEntityRepository, PancakesService pancakesService) {
        this.modelMapper = modelMapper;
        this.ordersEntityRepository = ordersEntityRepository;
        this.pancakesService = pancakesService;
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
    public void delete(Integer id) throws NotFoundException {
        ordersEntityRepository.deleteById(id);
    }
    @Transactional
    @Override
    public Order insert(OrderRequest orderRequest)  throws NotFoundException, ConflictException , PancakeException {
        if(!hasCorrectPancake(orderRequest))
            throw new PancakeException();
        OrdersEntity ordersEntity = modelMapper.map(orderRequest, OrdersEntity.class);
        ordersEntity.setId(null);
        Instant instantMoment = Instant.now();
        Timestamp nowDateTime = Timestamp.from(instantMoment);
        ordersEntity.setOrderDatetime(nowDateTime);
        ordersEntity = ordersEntityRepository.saveAndFlush(ordersEntity);
        entityManager.refresh(ordersEntity);
        return findById(ordersEntity.getId());
    }
    private boolean hasCorrectPancake(OrderRequest order){
        if(order.getPancakes().toArray().length == 0)
            return false;
        for (Pancake x : order.getPancakes()){
            if (!pancakesService.isCorrectPancake(x))
                return false;
        }
        return true;
    }

    @Transactional
    public Order update(Integer id, OrderRequest orderRequest) throws NotFoundException, ConflictException {
        OrdersEntity ordersEntity = modelMapper.map(orderRequest, OrdersEntity.class);
        ordersEntity.setId(id);
        ordersEntity = ordersEntityRepository.saveAndFlush(ordersEntity);
        entityManager.refresh(ordersEntity);
        return findById(ordersEntity.getId());
    }


}
