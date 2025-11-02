package com.sparta.demo.domain.order.dto;

import lombok.Getter;

@Getter
public class OrderRequestDto {
    private Long userId;
    private Long productId;
    private Integer quantity;
    private String shippingAddress;
}


