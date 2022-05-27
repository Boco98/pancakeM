package com.example.pancakem.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "pancakes_ingredients")
@IdClass(PancakesIngredientsEntityPK.class)
public class PancakesIngredientsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "pancake_id", nullable = false)
    private Integer pancakeId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ingedient_id", nullable = false)
    private Integer ingedientId;
    @ManyToOne
    @JoinColumn(name = "pancake_id", referencedColumnName = "id", nullable = false)
    private PancakesEntity pancakes;
    @ManyToOne
    @JoinColumn(name = "ingedient_id", referencedColumnName = "id", nullable = false)
    private IngredientsEntity ingredients;

}
