package com.orderservice.order_management.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {

    @NotNull(message = "Customer Id is required")
    private Long customerId;

    @NotEmpty(message = "Order must contain at least one item")
    private List<@Valid OrderItemDTO> items;
}
