package com.example.pancakem.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "discounts")
public class DiscountsEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "discount", nullable = true)
    private Integer discount;
    @OneToMany(mappedBy = "discounts")
    @JsonIgnore
    private List<OrdersEntity> orders;

}
