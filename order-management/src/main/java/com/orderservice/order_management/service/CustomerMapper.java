package com.orderservice.order_management.service;

import com.orderservice.order_management.model.dto.CustomerDTO;
import com.orderservice.order_management.model.enity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toCustomerEntity(CustomerDTO dto) {
        return Customer.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
    }

    public CustomerDTO toCustomerDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .build();
    }
}
