package com.example.kakao_tesk.repository;

import com.example.kakao_tesk.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {
}
