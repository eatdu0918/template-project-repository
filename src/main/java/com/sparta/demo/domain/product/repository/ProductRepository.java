package com.sparta.demo.domain.product.repository;

import com.sparta.demo.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    // 상품명 검색
    List<Product> findByNameContaining(String keyword);

    long countByCategory_Id(Long categoryId);
}
