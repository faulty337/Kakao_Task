package com.example.kakao_tesk.entity;

import com.example.kakao_tesk.type.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Rank extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer price;

    private String name;


    private Category category;

    public Rank(Integer price, String name, Category category) {
        this.price = price;
        this.name = name;
        this.category = category;
    }
}
