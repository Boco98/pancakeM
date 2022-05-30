package com.example.pancakem.models;

import com.example.pancakem.models.entities.PancakesEntity;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class OrderRequest {
    private Integer id;
    private Timestamp orderDatetime;
    private String description;
    private Integer discountsId;
    private List<Pancake> pancakes;
}
