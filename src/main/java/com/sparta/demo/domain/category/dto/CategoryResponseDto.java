package com.sparta.demo.domain.category.dto;

import com.sparta.demo.domain.category.entity.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryResponseDto {
    private Long id;
    private String name;
    private Long parentId;
    private String description;
    private Long productCount;

    public static CategoryResponseDto from(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .parentId(category.getParent() != null ? category.getParent().getId() : null)
                .description(category.getDescription())
                .productCount(null)
                .build();
    }

    public static CategoryResponseDto from(Category category, Long productCount) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .parentId(category.getParent() != null ? category.getParent().getId() : null)
                .description(category.getDescription())
                .productCount(productCount)
                .build();
    }
}


