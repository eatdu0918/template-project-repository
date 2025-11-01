package com.sparta.demo.domain.product.dto;

import com.sparta.demo.domain.product.entity.Category;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CategoryResponseDto {
    private Long id;
    private String name;
    private Long parentId;
    private String description;

    public static CategoryResponseDto from(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .parentId(category.getParent() != null ? category.getParent().getId() : null)
                .description(category.getDescription())
                .build();
    }
}
