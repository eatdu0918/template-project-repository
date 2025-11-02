package com.sparta.demo.domain.category.controller;

import com.sparta.demo.domain.category.dto.CategoryRequestDto;
import com.sparta.demo.domain.category.dto.CategoryResponseDto;
import com.sparta.demo.domain.category.service.CategoryService;
import com.sparta.demo.global.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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

    @PutMapping
    public ApiResponse<CategoryResponseDto> updateCategory(@RequestBody CategoryRequestDto request) {
        return ApiResponse.success(categoryService.updateCategory(request));
    }

    @GetMapping
    public ApiResponse<List<CategoryResponseDto>> getCategories() {
        return ApiResponse.success(categoryService.getCategories());
    }
}


