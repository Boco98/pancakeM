package com.example.pancakem.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "ingredient_categories")
public class IngredientCategoriesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "category_name", nullable = false, length = 45)
    private String categoryName;
    @OneToMany(mappedBy = "ingredientCategories")
    private List<IngredientsEntity> ingredients;

}
