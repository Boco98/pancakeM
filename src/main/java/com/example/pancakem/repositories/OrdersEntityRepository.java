package com.example.pancakem.repositories;

import com.example.pancakem.models.entities.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersEntityRepository extends JpaRepository<OrdersEntity, Integer> {
}
