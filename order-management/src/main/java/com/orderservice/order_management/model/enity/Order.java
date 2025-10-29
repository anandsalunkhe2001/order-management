package com.orderservice.order_management.model.enity;

import com.orderservice.order_management.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    // === AGGREGATE ROOT BUSINESS METHODS ===

    /**
     * Add an item to this order (controlled by aggregate root)
     */
    public void addOrderItem(String productName, BigDecimal unitPrice, Integer quantity) {
        // 1. Create new OrderItem linked to this Order
        OrderItem item = OrderItem.builder()
                .order(this)                    // This order is the parent
                .productName(productName)       // Snapshot product name
                .unitPrice(unitPrice)           // Snapshot price at order time
                .quantity(quantity)             // Quantity ordered
                .build();

        // 2. Initialize items list if null
        if (this.items == null) {
            this.items = new ArrayList<>();
        }

        // 3. Add item to order
        this.items.add(item);

        // 4. Recalculate order total
        calculateTotalAmount();
    }

    /**
     * Private method to calculate total order amount
     * This is internal business logic of the aggregate
     */
    private void calculateTotalAmount() {
        this.totalAmount = this.items.stream()           // Stream through all items
                .map(item -> item.getUnitPrice()             // Get unit price
                        .multiply(BigDecimal.valueOf(item.getQuantity()))) // Price × Quantity
                .reduce(BigDecimal.ZERO, BigDecimal::add);   // Sum all item totals
    }
}
