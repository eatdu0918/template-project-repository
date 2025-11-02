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

import java.util.List;
import java.util.stream.Collectors;

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

    public ProductResponseDto getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PRODUCT));
        return ProductResponseDto.from(product);
    }

    public List<ProductResponseDto> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductResponseDto::from)
                .collect(Collectors.toList());
    }

    public ProductResponseDto updateProduct(ProductRequestDto request) {
        Product product = productRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PRODUCT));

        Product updatedProduct = Product.builder()
                .category(product.getCategory())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .build();
        product.update(updatedProduct);
        productRepository.save(product);
        return ProductResponseDto.from(product);
    }
}
