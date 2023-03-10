package com.example.kakao_tesk.repository;

import com.example.kakao_tesk.entity.Menu;
import com.example.kakao_tesk.type.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findAllByCategory(Category category);
}
