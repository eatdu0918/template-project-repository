package com.sparta.demo.domain.refund.dto;

import com.sparta.demo.domain.refund.entity.Refund;
import com.sparta.demo.domain.refund.entity.RefundStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class RefundResponseDto {
    private Long id;
    private Long userId;
    private Long orderId;
    private String reason;
    private RefundStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static RefundResponseDto from(Refund refund) {
        return RefundResponseDto.builder()
                .id(refund.getId())
                .userId(refund.getUserId())
                .orderId(refund.getOrder().getId())
                .reason(refund.getReason())
                .status(refund.getStatus())
                .createdAt(refund.getCreatedAt())
                .updatedAt(refund.getUpdatedAt())
                .build();
    }
}


