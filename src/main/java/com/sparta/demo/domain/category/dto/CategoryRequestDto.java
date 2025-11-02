package com.sparta.demo.domain.category.dto;

import lombok.Getter;

@Getter
public class CategoryRequestDto {
    private Long id;
    private String name;
    private String description;
    private Long parentId;
}


