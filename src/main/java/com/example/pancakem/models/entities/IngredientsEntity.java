package com.example.pancakem.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "ingredients")
public class IngredientsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @Basic
    @Column(name = "price", nullable = false, precision = 1)
    private BigDecimal price;
    @Basic
    @Column(name = "healthy_ingredient", nullable = true)
    private Boolean healthyIngredient;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id" ,referencedColumnName="id" , nullable = false)
    private IngredientCategoriesEntity ingredient_categories;

    @ManyToMany(mappedBy = "ingredients", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<PancakesEntity> pancakes = new HashSet<>();
}
