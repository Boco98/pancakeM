package com.example.pancakem.models.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "orders", schema = "pancake1", catalog = "")
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
    @ManyToOne
    @JoinColumn(name = "discount_id", referencedColumnName = "id", nullable = false)
    private DiscountsEntity discounts;
    @OneToMany(mappedBy = "orders")
    private List<OrdersPancakesEntity> orders;

}
