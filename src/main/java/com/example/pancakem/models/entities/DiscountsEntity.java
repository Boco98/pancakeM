package com.example.pancakem.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "discounts")
public class DiscountsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "discount", nullable = true)
    private Integer discount;
    @OneToMany(mappedBy = "discounts")
    private List<OrdersEntity> orders;

}
