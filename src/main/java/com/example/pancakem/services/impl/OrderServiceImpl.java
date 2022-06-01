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
import java.util.ArrayList;
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
        entityManager.refresh(ordersEntity);
        ordersEntity.setDiscounts(modelMapper.map(discountsService.findById(calculateDiscountId(ordersEntity)),DiscountsEntity.class ));
        ordersEntity = ordersEntityRepository.saveAndFlush(ordersEntity);
        entityManager.refresh(ordersEntity);
        return findById(ordersEntity.getId());
    }
    private Integer calculateDiscountId(OrdersEntity ordersEntity){
        SingleOrder order= modelMapper.map(ordersEntity, SingleOrder.class);
        Integer discountId=1;
        if(has75Discount(order)){
            discountId=3;
        }else{
            Double priceD = calculatePrice(order).doubleValue();
            if(priceD<20) discountId=4; //discount=0%
            else if ( (priceD>=20) && (priceD<50)) discountId=1;//5%
            else if ( (priceD>50)   ) discountId=2;//10%
        }

        return discountId;
    }

    private boolean has75Discount(SingleOrder order){
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
        if(order.getDiscountsId()==3){//15% on every healty pancake
            order.setPriceWithDiscount(calculatePrice85(order.getPancakes()));
            order.setOrderDiscountString("Discount: 15% discount on every healty pancake" );
        }else{
            Integer discount = discountsService.findById(order.getDiscountsId()).getDiscount();
            order.setPriceWithDiscount( calculatePriceWithDiscount(order.getPrice(), discount)   );
            order.setOrderDiscountString("Discount: " + discount + " %" );
        }

        return order;
    }
    public SingleOrderWithPriceAndPancakesPrices findByIdWithPriceAndPancakePrices( Integer id) throws  NotFoundException{
        SingleOrderWithPrice order1 = findOrderPriceById(id);
        SingleOrderWithPriceAndPancakesPrices newOrder = new SingleOrderWithPriceAndPancakesPrices();
        newOrder.setId(id);
        newOrder.setDiscountsId(order1.getDiscountsId());
        newOrder.setPrice((order1.getPrice()));
        newOrder.setDescription(order1.getDescription());
        newOrder.setOrderDatetime(order1.getOrderDatetime());
        newOrder.setPriceWithDiscount(order1.getPriceWithDiscount());
        newOrder.setOrderDiscountString(order1.getOrderDiscountString());
        List<Pancake> list1 = order1.getPancakes();
        List<PancakeWithPriceAndDiscount> list2 = new ArrayList<PancakeWithPriceAndDiscount>();
        for(Pancake x : list1){
            BigDecimal y =  pancakesService.calculatePrice(x.getId());
            Double littlePrice= y.doubleValue();
            if(healtyPancake(x)){
                littlePrice = littlePrice*85/100;
            }
            list2.add(new PancakeWithPriceAndDiscount(x,y, new BigDecimal(littlePrice.toString())));
        }
        newOrder.setPancakes(list2);
        return newOrder;

    }
    private BigDecimal calculatePrice85(List<Pancake> pancakes){
        BigDecimal sum = new BigDecimal(0);
        for(Pancake x: pancakes){
            if(healtyPancake(x)){
                Double littlePrice = pancakesService.calculatePrice(x.getId()).doubleValue();
                littlePrice = littlePrice*85/100;
                sum =  sum.add(new BigDecimal(littlePrice.toString()));
            }else{
                sum = sum.add(pancakesService.calculatePrice(x.getId()));
                System.out.println("sum:"+sum);
            }
        }
        return sum;
    }
    private boolean healtyPancake(Pancake p){
        SinglePancake pancake = pancakesService.findById(p.getId());
        Integer numOfIngredients = pancake.getIngredients().toArray().length;
        Integer numOfHealtyIngredients = 0;
        for(Ingredient x: pancake.getIngredients()){
            if(x.getHealthyIngredient()) numOfHealtyIngredients++;
        }

        return  ( (numOfIngredients.doubleValue()/100*75) <= numOfHealtyIngredients   );
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
