package com.sparta.demo.domain.product.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductRequestDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private Long categoryId;
}
