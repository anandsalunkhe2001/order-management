package com.orderservice.order_management.model.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

    @NotBlank(message = "Product name is required")      // Not null/empty/whitespace
    private String productName;

    @NotNull(message = "Unit price is required")         // Not null
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal unitPrice;

    @NotNull(message = "Quantity is required")           // Not null
    @Min(value = 1, message = "Quantity must be at least 1") // Minimum 1
    private Integer quantity;
}
