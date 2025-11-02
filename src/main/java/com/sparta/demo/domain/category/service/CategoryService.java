package com.sparta.demo.domain.category.service;

import com.sparta.demo.domain.category.dto.CategoryRequestDto;
import com.sparta.demo.domain.category.dto.CategoryResponseDto;
import com.sparta.demo.domain.category.entity.Category;
import com.sparta.demo.domain.category.repository.CategoryRepository;
import com.sparta.demo.domain.product.repository.ProductRepository;
import com.sparta.demo.global.exception.CustomException;
import com.sparta.demo.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Transactional
    public CategoryResponseDto createCategory(CategoryRequestDto request) {
        Category parent = null;
        if (request.getParentId() != null) {
            parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("부모 카테고리를 찾을 수 없습니다. ID: " + request.getParentId()));
        }

        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .parent(parent)
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

        Category parent = null;
        if (request.getParentId() != null) {
            parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CATEGORY));
        }

        Category updatedCategory = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .parent(parent)
                .build();

        category.update(updatedCategory);

        return CategoryResponseDto.from(category);
    }

    public java.util.List<CategoryResponseDto> getCategories() {
        java.util.List<com.sparta.demo.domain.category.entity.Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(c -> CategoryResponseDto.from(c, productRepository.countByCategory_Id(c.getId())))
                .toList();
    }
}


