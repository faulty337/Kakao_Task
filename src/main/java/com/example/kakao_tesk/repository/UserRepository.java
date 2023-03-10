package com.example.kakao_tesk.repository;

import com.example.kakao_tesk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
