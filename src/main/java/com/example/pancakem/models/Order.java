package com.example.pancakem.models;
import lombok.Data;


import java.sql.Timestamp;
@Data
public class Order {
    private Integer id;
    private Timestamp orderDatetime;
    private String description;
    private Integer discountsId;
}
