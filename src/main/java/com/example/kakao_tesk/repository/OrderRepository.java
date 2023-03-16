package com.example.kakao_tesk.repository;

import com.example.kakao_tesk.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findTop3AllByCreatedAtBefore(LocalDateTime localDateTime);
}
