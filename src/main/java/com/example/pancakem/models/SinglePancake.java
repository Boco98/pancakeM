package com.example.pancakem.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SinglePancake extends Pancake {
    List<Ingredient> ingredients;
}
