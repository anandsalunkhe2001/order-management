package com.orderservice.order_management.service;

import com.orderservice.order_management.model.enity.Order;
import com.orderservice.order_management.model.enums.OrderStatus;
import com.orderservice.order_management.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    private OrderService orderService;
    @BeforeEach
    void setUp()
    {
        orderService = new OrderService(orderRepository);
    }

    @Test
    void shouldCreateOrderSuccessfully() {
        // ARRANGE - Setup test data and expectations
        Order inputOrder = Order.builder()
                .status(OrderStatus.PENDING)
                .totalAmount(BigDecimal.valueOf(100.0))
                .build();

        Order expectedSavedOrder = Order.builder()
                .id(1L)  // ID that should be returned after save
                .status(OrderStatus.PENDING)
                .totalAmount(BigDecimal.valueOf(100.0))
                .build();

        // Mock the repository behavior
        when(orderRepository.save(any(Order.class))).thenReturn(expectedSavedOrder);

        // ACT - Call the method we're testing
        Order actualResult = orderService.createOrder(inputOrder);

        // ASSERT - Verify the results
        assertNotNull(actualResult, "Returned order should not be null");
        assertEquals(1L, actualResult.getId(), "Order ID should be 1");
        assertEquals(OrderStatus.PENDING, actualResult.getStatus(), "Status should be PENDING");
        assertEquals(BigDecimal.valueOf(100.0), actualResult.getTotalAmount(), "Total amount should be 100.0");

        // Verify repository interaction
        verify(orderRepository, times(1)).save(inputOrder);
    }


}
