package com.sparta.demo.domain.product.controller;

import com.sparta.demo.domain.product.dto.ProductRequestDto;
import com.sparta.demo.domain.product.dto.ProductResponseDto;
import com.sparta.demo.domain.product.service.ProductService;
import com.sparta.demo.global.common.ApiResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.sparta.demo.domain.product.dto.ProductFilterRequestDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ApiResponse<ProductResponseDto> registrationProduct(@RequestBody ProductRequestDto request) {
        return ApiResponse.success(productService.registration(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponseDto> getProduct(@PathVariable Long id) {
        return ApiResponse.success(productService.getProduct(id));
    }

    @GetMapping
    public ApiResponse<List<ProductResponseDto>> getProducts() {
        return ApiResponse.success(productService.getProducts());
    }

    @PostMapping("/search")
    public ApiResponse<List<ProductResponseDto>> getProductsByFilter(@RequestBody ProductFilterRequestDto request) {
        return ApiResponse.success(
                productService.getProductsByFilter(
                        request.getCategoryId(),
                        request.getMinPrice(),
                        request.getMaxPrice(),
                        request.getKeyword()
                )
        );
    }

    @PutMapping
    public ApiResponse<ProductResponseDto> updateProduct(@RequestBody ProductRequestDto request) {
        return ApiResponse.success(productService.updateProduct(request));
    }
}
