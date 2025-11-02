package com.sparta.demo.domain.order.service;

import com.sparta.demo.domain.order.dto.OrderRequestDto;
import com.sparta.demo.domain.order.dto.OrderResponseDto;
import com.sparta.demo.domain.order.dto.OrderStatusUpdateRequestDto;
import com.sparta.demo.domain.order.entity.Order;
import com.sparta.demo.domain.order.entity.OrderStatus;
import com.sparta.demo.domain.order.repository.OrderRepository;
import com.sparta.demo.domain.product.entity.Product;
import com.sparta.demo.domain.product.repository.ProductRepository;
import com.sparta.demo.global.exception.CustomException;
import com.sparta.demo.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PRODUCT));

        if (product.getStock() < request.getQuantity()) {
            throw new CustomException(ErrorCode.INSUFFICIENT_STOCK);
        }

        product.update(Product.builder()
                .id(product.getId())
                .category(product.getCategory())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock() - request.getQuantity())
                .build());
        productRepository.save(product);

        Order order = Order.builder()
                .userId(request.getUserId())
                .product(product)
                .quantity(request.getQuantity())
                .shippingAddress(request.getShippingAddress())
                .status(OrderStatus.PENDING)
                .build();

        orderRepository.save(order);
        return OrderResponseDto.from(order);
    }

    public List<OrderResponseDto> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(OrderResponseDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderResponseDto updateOrderStatus(OrderStatusUpdateRequestDto request) {
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ORDER));

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new CustomException(ErrorCode.INVALID_ORDER_STATUS);
        }

        order.updateStatus(request.getStatus());

        if (request.getStatus() == OrderStatus.CANCELED) {
            Product product = order.getProduct();
            product.update(Product.builder()
                    .id(product.getId())
                    .category(product.getCategory())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .stock(product.getStock() + order.getQuantity())
                    .build());
            productRepository.save(product);
        }

        return OrderResponseDto.from(order);
    }

    @Transactional
    public OrderResponseDto cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ORDER));

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new CustomException(ErrorCode.INVALID_ORDER_STATUS);
        }

        // set status to CANCELED and restore stock
        order.updateStatus(OrderStatus.CANCELED);

        Product product = order.getProduct();
        product.update(Product.builder()
                .id(product.getId())
                .category(product.getCategory())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock() + order.getQuantity())
                .build());
        productRepository.save(product);

        return OrderResponseDto.from(order);
    }
}


