package com.sparta.demo.domain.refund.dto;

import com.sparta.demo.domain.refund.entity.RefundStatus;
import lombok.Getter;

@Getter
public class RefundProcessRequestDto {
    private Long refundId;
    private RefundStatus status; // APPROVED or REJECTED
}


