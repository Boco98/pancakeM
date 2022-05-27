package com.example.pancakem.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "orders_pancakes")
@IdClass(OrdersPancakesEntityPK.class)
public class OrdersPancakesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id", nullable = false)
    private Integer orderId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "pancake1_id", nullable = false)
    private Integer pancake1Id;
    @Basic
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private OrdersEntity orders;
    @ManyToOne
    @JoinColumn(name = "pancake1_id", referencedColumnName = "id", nullable = false)
    private PancakesEntity pancakes;

}
