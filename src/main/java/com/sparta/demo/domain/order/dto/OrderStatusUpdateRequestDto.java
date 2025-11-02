package com.sparta.demo.domain.order.dto;

import com.sparta.demo.domain.order.entity.OrderStatus;
import lombok.Getter;

@Getter
public class OrderStatusUpdateRequestDto {
    private Long orderId;
    private OrderStatus status;
}


