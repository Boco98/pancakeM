package com.example.pancakem.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "pancakes")
public class PancakesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "name", nullable = false, length = 45)
    private String name;

}
