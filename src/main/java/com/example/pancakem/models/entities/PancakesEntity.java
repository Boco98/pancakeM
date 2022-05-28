package com.example.pancakem.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.xml.bind.v2.runtime.reflect.Lister;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "pancakes")
public class PancakesEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "pancakes_ingredients",
            joinColumns = {
            @JoinColumn(name = "pancake_id" , referencedColumnName = "id")},
            inverseJoinColumns = {
            @JoinColumn(name = "ingredient_id", referencedColumnName = "id")})
    @JsonIgnore
    private Set<IngredientsEntity> ingredients  = new HashSet<>();
}
