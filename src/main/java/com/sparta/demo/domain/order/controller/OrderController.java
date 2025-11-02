package com.sparta.demo.domain.order.controller;

import com.sparta.demo.domain.order.dto.OrderRequestDto;
import com.sparta.demo.domain.order.dto.OrderResponseDto;
import com.sparta.demo.domain.order.dto.OrderStatusUpdateRequestDto;
import com.sparta.demo.domain.order.service.OrderService;
import com.sparta.demo.global.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ApiResponse<OrderResponseDto> createOrder(@RequestBody OrderRequestDto request) {
        return ApiResponse.success(orderService.createOrder(request));
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<OrderResponseDto>> getOrdersByUser(@PathVariable Long userId) {
        return ApiResponse.success(orderService.getOrdersByUser(userId));
    }

    @PutMapping("/status")
    public ApiResponse<OrderResponseDto> updateOrderStatus(@RequestBody OrderStatusUpdateRequestDto request) {
        return ApiResponse.success(orderService.updateOrderStatus(request));
    }

    @PostMapping("/{orderId}/cancel")
    public ApiResponse<OrderResponseDto> cancelOrder(@PathVariable Long orderId) {
        return ApiResponse.success(orderService.cancelOrder(orderId));
    }
}


