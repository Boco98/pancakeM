package com.example.pancakem.services.impl;

import com.example.pancakem.exceptions.ConflictException;
import com.example.pancakem.exceptions.NotFoundException;
import com.example.pancakem.exceptions.PancakeException;
import com.example.pancakem.models.*;
import com.example.pancakem.models.entities.DiscountsEntity;
import com.example.pancakem.models.entities.OrdersEntity;
import com.example.pancakem.repositories.OrdersEntityRepository;
import com.example.pancakem.services.DiscountsService;
import com.example.pancakem.services.OrdersService;
import com.example.pancakem.services.PancakesService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrdersService {

    private final ModelMapper modelMapper;
    private final OrdersEntityRepository ordersEntityRepository;
    private DiscountsService discountsService;

    private final PancakesService pancakesService;
    @PersistenceContext
    private EntityManager entityManager;
    public OrderServiceImpl(ModelMapper modelMapper, OrdersEntityRepository ordersEntityRepository, DiscountsService discountsService, PancakesService pancakesService) {
        this.modelMapper = modelMapper;
        this.ordersEntityRepository = ordersEntityRepository;
        this.discountsService = discountsService;
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
        orderRequest.setDiscountsId(4);//default
        if(!hasCorrectPancake(orderRequest))
            throw new PancakeException();
        OrdersEntity ordersEntity = modelMapper.map(orderRequest, OrdersEntity.class);
        ordersEntity.setId(null);

        Instant instantMoment = Instant.now();
        Timestamp nowDateTime = Timestamp.from(instantMoment);
        ordersEntity.setOrderDatetime(nowDateTime);
        ordersEntity = ordersEntityRepository.saveAndFlush(ordersEntity);
        if(has75Discount(orderRequest)){
            ordersEntity.setDiscounts(modelMapper.map( discountsService.findById(3), DiscountsEntity.class));
        }else{
            Integer discountID = changeDiscountNOT75(ordersEntity);
        }
        ordersEntity = ordersEntityRepository.saveAndFlush(ordersEntity);
        entityManager.refresh(ordersEntity);
        return findById(ordersEntity.getId());
    }
    private Integer changeDiscountNOT75(OrdersEntity order){
        Integer discountId=1;
        Double priceD = calculatePrice(modelMapper.map(order, SingleOrder.class)).doubleValue();
        if(priceD<20) discountId=4;
        else if ( (priceD>=20) && (priceD<50)) discountId=1;
        else if ( (priceD>50)   ) discountId=2;
        order.setDiscounts(modelMapper.map( discountsService.findById(discountId), DiscountsEntity.class));
        return discountId;
    }

    private boolean has75Discount(OrderRequest order){
        Integer numOfIngredients =0;
        Integer numOfHealtyIngredients=0;
        for(Pancake x : order.getPancakes()){
            List<Ingredient> listIngredients = pancakesService.findById(x.getId()).getIngredients();
            numOfIngredients+=listIngredients.toArray().length;
            for(Ingredient y : listIngredients){
                if(y.getHealthyIngredient()) numOfHealtyIngredients++;
            }
        }

        return  ( (numOfIngredients.doubleValue()/100*75) <= numOfHealtyIngredients   );

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


    public SingleOrderWithPrice findOrderPriceById(Integer id) throws  NotFoundException{
        SingleOrder parentOrder= findById(id);
        SingleOrderWithPrice order =   modelMapper.map(parentOrder, SingleOrderWithPrice.class);
        order.setPrice(calculatePrice(parentOrder));

        order.setPriceWithDiscount(calculatePriceWithDiscount(order.getPrice(),order.getDiscountsId()));
        Integer discount = discountsService.findById(order.getDiscountsId()).getDiscount();
        order.setOrderDiscount("Discount: " + discount + " %" );
        return order;
    }

    private BigDecimal calculatePrice(SingleOrder singleOrder){
        BigDecimal sum = BigDecimal.ZERO;
        for(Pancake x: singleOrder.getPancakes()){
            sum = sum.add( pancakesService.calculatePrice(x.getId()));
        }
        return sum;
    }
    private BigDecimal calculatePriceWithDiscount(BigDecimal price, Integer discount){
        Double discountD = price.doubleValue()*(discount.doubleValue()/100.00);
        Double resultPrice = price.doubleValue() - discountD;
        return new BigDecimal(resultPrice.toString()); //constructor BigDecimal(Double) is unpredictable;
    }
}
