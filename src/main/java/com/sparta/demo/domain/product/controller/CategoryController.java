package com.sparta.demo.domain.product.controller;

import com.sparta.demo.domain.product.dto.CategoryRequestDto;
import com.sparta.demo.domain.product.dto.CategoryResponseDto;
import com.sparta.demo.domain.product.service.CategoryService;
import com.sparta.demo.global.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ApiResponse<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto request) {
        return ApiResponse.success(categoryService.createCategory(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<CategoryResponseDto> getCategory(@PathVariable Long id) {
        return ApiResponse.success(categoryService.getCategory(id));
    }


}
