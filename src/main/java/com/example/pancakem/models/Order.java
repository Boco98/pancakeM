package com.example.pancakem.models;

import com.example.pancakem.models.entities.DiscountsEntity;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
@Data
public class Order {
    private Integer id;
    private Timestamp orderDatetime;
    private String description;
    private Integer discountsId;
}
