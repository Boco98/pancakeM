package com.example.pancakem.models;

import lombok.Data;

import java.util.List;

@Data
public class SinglePancakeRequest {
    private Integer id;
    private String name;
    List<Ingredient> ingredients;
}
