package com.example.kakao_tesk.repository;

import com.example.kakao_tesk.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
