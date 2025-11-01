package com.sparta.demo.domain.product.repository;

import com.sparta.demo.domain.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
