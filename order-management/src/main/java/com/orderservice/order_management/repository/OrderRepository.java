package com.orderservice.order_management.repository;

import com.orderservice.order_management.model.enity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
