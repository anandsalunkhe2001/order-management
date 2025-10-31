package com.orderservice.order_management.service;


import com.orderservice.order_management.exception.ResourceNotFoundException;
import com.orderservice.order_management.model.dto.OrderRequestDTO;
import com.orderservice.order_management.model.dto.OrderResponseDTO;
import com.orderservice.order_management.model.enity.Customer;
import com.orderservice.order_management.model.enity.Order;
import com.orderservice.order_management.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper; // Add mapper dependency
    private final CustomerService customerService; // Inject CustomerService


    public OrderResponseDTO createOrder(OrderRequestDTO orderRequest) {

        // Validate customer exists using CustomerService
        customerService.validateCustomerExists(orderRequest.getCustomerId());

        // Convert DTO to Entity using mapper
        Order order = orderMapper.toOrderEntity(orderRequest);

        // Business logic: Calculate total
        order.calculateTotalAmount();

        // Save order (cascades to order items)
        Order savedOrder = orderRepository.save(order);

        // Convert back to DTO for response
        return orderMapper.toDTO(savedOrder);
    }

    @Transactional(readOnly = true)
    public OrderResponseDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        return orderMapper.toDTO(order);
    }

    @Transactional(readOnly = true)
    public List<OrderResponseDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }
}
