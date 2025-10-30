package com.orderservice.order_management.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.orderservice.order_management.model.dto.CustomerDTO;
import com.orderservice.order_management.model.dto.OrderItemDTO;
import com.orderservice.order_management.model.dto.OrderRequestDTO;
import com.orderservice.order_management.model.dto.OrderResponseDTO;
import com.orderservice.order_management.model.enity.Customer;
import com.orderservice.order_management.model.enity.Order;
import com.orderservice.order_management.model.enity.OrderItem;
import com.orderservice.order_management.model.enums.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toOrderEntity(OrderRequestDTO dto) {
        Customer customer = new Customer();
        customer.setId(dto.getCustomerId());

        Order order = Order.builder()
                .customer(customer)
                .status(OrderStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        // Add order items
        if (dto.getItems() != null) {
            for (OrderItemDTO itemDto : dto.getItems()) {
                order.addOrderItem(
                        itemDto.getProductName(),
                        itemDto.getUnitPrice(),
                        itemDto.getQuantity()
                );
            }
        }

        return order;
    }

    public OrderResponseDTO toDTO(Order order) {
        return OrderResponseDTO.builder()
                .id(order.getId())
                .customer(toCustomerDTO(order.getCustomer()))
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .items(toOrderItemDTOs(order.getItems()))
                .createdAt(order.getCreatedAt())
                .build();
    }

    private CustomerDTO toCustomerDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .build();
    }

    private List<OrderItemDTO> toOrderItemDTOs(List<OrderItem> items) {
        return items.stream()
                .map(this::toOrderItemDTO)
                .collect(Collectors.toList());
    }

    private OrderItemDTO toOrderItemDTO(OrderItem item) {
        return OrderItemDTO.builder()
                .productName(item.getProductName())
                .unitPrice(item.getUnitPrice())
                .quantity(item.getQuantity())
                .build();
    }
}
