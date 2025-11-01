package com.sparta.demo.domain.product.controller;

import com.sparta.demo.domain.product.dto.ProductRequestDto;
import com.sparta.demo.domain.product.dto.ProductResponseDto;
import com.sparta.demo.domain.product.service.ProductService;
import com.sparta.demo.global.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ApiResponse<ProductResponseDto> registrationProduct(@RequestBody ProductRequestDto request) {
        return ApiResponse.success(productService.registration(request));
    }
}
