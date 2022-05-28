package com.example.pancakem.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class OrdersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "order_datetime", nullable = false)
    private Timestamp orderDatetime;
    @Basic
    @Column(name = "description", nullable = true, length = 200)
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private DiscountsEntity discounts;

    @ManyToMany(fetch = FetchType.LAZY, cascade =  CascadeType.PERSIST)
    @JoinTable(name = "orders_pancakes",
            joinColumns = {
                    @JoinColumn(name = "order_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "pancake1_id", referencedColumnName = "id")})
    @JsonIgnore
    private List<PancakesEntity> pancakes;
}


