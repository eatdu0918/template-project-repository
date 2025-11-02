package com.sparta.demo.domain.product.service;

import com.sparta.demo.domain.product.dto.CategoryRequestDto;
import com.sparta.demo.domain.product.dto.CategoryResponseDto;
import com.sparta.demo.domain.product.entity.Category;
import com.sparta.demo.domain.product.repository.CategoryRepository;
import com.sparta.demo.global.exception.CustomException;
import com.sparta.demo.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public CategoryResponseDto createCategory(CategoryRequestDto request) {
        // parentId가 있으면 부모 카테고리 조회
        Category parent = null;
        if (request.getParentId() != null) {
            parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("부모 카테고리를 찾을 수 없습니다. ID: " + request.getParentId()));
        }

        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .parent(parent)  // 부모 카테고리 설정 (null이면 최상위 카테고리)
                .build();

        categoryRepository.save(category);
        return CategoryResponseDto.from(category);
    }

    public CategoryResponseDto getCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CATEGORY));

        return CategoryResponseDto.from(category);
    }

    @Transactional
    public CategoryResponseDto updateCategory(CategoryRequestDto request) {
        Category category = categoryRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CATEGORY));

        // parentId가 있으면 부모 카테고리 조회
        Category parent = null;
        if (request.getParentId() != null) {
            parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CATEGORY));
        }

        Category updatedCategory = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .parent(parent)  // 올바른 부모 카테고리 설정
                .build();

        category.update(updatedCategory);

        return CategoryResponseDto.from(category);
    }
}
