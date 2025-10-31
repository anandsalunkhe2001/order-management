package com.orderservice.order_management.controller;

import com.orderservice.order_management.model.dto.OrderRequestDTO;
import com.orderservice.order_management.model.dto.OrderResponseDTO;
//import com.orderservice.order_management.model.enity.Order;
import com.orderservice.order_management.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
//@Tag(name = "Order Controller", description = "APIs for managing orders")
public class OrderController {
    private final OrderService orderService;



//    @Operation(summary = "Create a new order", description = "Creates a new order in the system")
//    @ApiResponse(responseCode = "200", description = "Order created successfully")
//    @ApiResponse(responseCode = "400", description = "Invalid order data")
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO order) {
        OrderResponseDTO  savedOrder = orderService.createOrder(order);
        return ResponseEntity.ok(savedOrder);
    }


//    @Operation(summary = "Get order by ID", description = "Retrieves a specific order by its ID")
//    @ApiResponse(responseCode = "200", description = "Order found")
//    @ApiResponse(responseCode = "404", description = "Order not found")
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable Long id) {
        OrderResponseDTO  order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }


//    @Operation(summary = "Get all orders", description = "Retrieves all orders from the system")
//    @ApiResponse(responseCode = "200", description = "Orders retrieved successfully")
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<OrderResponseDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
}
