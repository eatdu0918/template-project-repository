package com.sparta.demo.domain.product.service;

import com.sparta.demo.domain.product.dto.ProductRequestDto;
import com.sparta.demo.domain.product.dto.ProductResponseDto;
import com.sparta.demo.domain.product.entity.Category;
import com.sparta.demo.domain.product.entity.Product;
import com.sparta.demo.domain.product.repository.CategoryRepository;
import com.sparta.demo.domain.product.repository.ProductRepository;
import com.sparta.demo.global.exception.CustomException;
import com.sparta.demo.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public ProductResponseDto registration(ProductRequestDto request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CATEGORY));

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .category(category)
                .build();

        productRepository.save(product);
        return ProductResponseDto.from(product);
    }
}
