package com.orderservice.order_management.repository;

import com.orderservice.order_management.model.enity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
