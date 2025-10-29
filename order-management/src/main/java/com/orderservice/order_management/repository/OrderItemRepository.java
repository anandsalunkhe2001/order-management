package com.orderservice.order_management.repository;

import com.orderservice.order_management.model.enity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
