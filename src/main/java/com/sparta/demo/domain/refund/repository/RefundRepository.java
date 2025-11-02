package com.sparta.demo.domain.refund.repository;

import com.sparta.demo.domain.refund.entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RefundRepository extends JpaRepository<Refund, Long> {
    List<Refund> findByUserId(Long userId);
}


