package com.sparta.demo.domain.refund.dto;

import lombok.Getter;

@Getter
public class RefundRequestDto {
    private Long userId;
    private Long orderId;
    private String reason;
}


