package com.sparta.demo.domain.product.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductFilterRequestDto {
    private Long categoryId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String keyword;
}


