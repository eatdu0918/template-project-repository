package com.sparta.demo.domain.refund.controller;

import com.sparta.demo.domain.refund.dto.RefundProcessRequestDto;
import com.sparta.demo.domain.refund.dto.RefundRequestDto;
import com.sparta.demo.domain.refund.dto.RefundResponseDto;
import com.sparta.demo.domain.refund.service.RefundService;
import com.sparta.demo.global.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/refunds")
public class RefundController {

    private final RefundService refundService;

    @PostMapping
    public ApiResponse<RefundResponseDto> requestRefund(@RequestBody RefundRequestDto request) {
        return ApiResponse.success(refundService.requestRefund(request));
    }

    @PutMapping
    public ApiResponse<RefundResponseDto> processRefund(@RequestBody RefundProcessRequestDto request) {
        return ApiResponse.success(refundService.processRefund(request));
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<RefundResponseDto>> getRefundsByUser(@PathVariable Long userId) {
        return ApiResponse.success(refundService.getRefundsByUser(userId));
    }
}


