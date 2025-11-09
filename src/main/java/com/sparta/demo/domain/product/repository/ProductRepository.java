package com.sparta.demo.domain.product.repository;

import com.sparta.demo.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    // 상품명 검색
    List<Product> findByNameContaining(String keyword);

    long countByCategoryId(Long categoryId);

//    List<Product> findeByName(String name);
//
//    boolean existsByName(String name);
//
//    List<Product> findByCategoryIdOrderByCreatedAtDesc(Long categoryId);
//
//    @Query("SELECT p FROM Product p WHERE p.stock <= :number")
//    List<Product> findLowStockProducts(@Param("number") int number);
}
