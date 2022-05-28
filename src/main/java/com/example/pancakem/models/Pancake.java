package com.example.pancakem.models;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;

@Data
public class Pancake {
    private Integer id;
    private String name;
}
