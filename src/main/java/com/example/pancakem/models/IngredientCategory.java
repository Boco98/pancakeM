package com.example.pancakem.models;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;

@Data
public class IngredientCategory {
    private Integer id;
    private String categoryName;
}
