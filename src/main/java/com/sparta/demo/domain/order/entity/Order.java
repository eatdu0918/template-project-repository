package com.sparta.demo.domain.order.entity;

import com.sparta.demo.domain.product.entity.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "purchase")
@Entity
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    Product product;

    @Column(nullable = false)
    Integer quantity;

    @Column(nullable = false)
    BigDecimal totalPrice;

    @Column(name = "shopping_address", nullable = false)
    String shippingAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    OrderStatus status;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    LocalDateTime updatedAt;

    @Builder
    public Order(Long id, Long userId, Product product, Integer quantity, BigDecimal totalPrice, String shippingAddress, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.product = product;
        this.quantity = quantity;
        this.shippingAddress = shippingAddress;
        this.status = status;
    }

    public void updateStatus(OrderStatus status) {
        this.status = status;
    }
}


