package com.sparta.demo.domain.refund.service;

import com.sparta.demo.domain.order.entity.Order;
import com.sparta.demo.domain.order.repository.OrderRepository;
import com.sparta.demo.domain.product.entity.Product;
import com.sparta.demo.domain.product.repository.ProductRepository;
import com.sparta.demo.domain.refund.dto.RefundProcessRequestDto;
import com.sparta.demo.domain.refund.dto.RefundRequestDto;
import com.sparta.demo.domain.refund.dto.RefundResponseDto;
import com.sparta.demo.domain.refund.entity.Refund;
import com.sparta.demo.domain.refund.entity.RefundStatus;
import com.sparta.demo.domain.refund.repository.RefundRepository;
import com.sparta.demo.global.exception.CustomException;
import com.sparta.demo.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RefundService {

    private final RefundRepository refundRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public RefundResponseDto requestRefund(RefundRequestDto request) {
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ORDER));

        Refund refund = Refund.builder()
                .userId(request.getUserId())
                .order(order)
                .reason(request.getReason())
                .status(RefundStatus.PENDING)
                .build();
        refundRepository.save(refund);
        return RefundResponseDto.from(refund);
    }

    public List<RefundResponseDto> getRefundsByUser(Long userId) {
        return refundRepository.findByUserId(userId).stream()
                .map(RefundResponseDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public RefundResponseDto processRefund(RefundProcessRequestDto request) {
        Refund refund = refundRepository.findById(request.getRefundId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_REFUND));

        if (refund.getStatus() != RefundStatus.PENDING) {
            throw new CustomException(ErrorCode.INVALID_REFUND_STATUS);
        }

        if (request.getStatus() == RefundStatus.APPROVED) {
            Order order = refund.getOrder();
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

        refund.updateStatus(request.getStatus());
        return RefundResponseDto.from(refund);
    }
}


