package com.example.pancakem.models.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "ingredients", schema = "pancake1", catalog = "")
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
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private IngredientCategoriesEntity ingredientCategories;
    @OneToMany(mappedBy = "ingredients")
    private List<PancakesIngredientsEntity> pancakesIngredients;

}
