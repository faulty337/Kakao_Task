package com.example.kakao_tesk.entity;

import com.example.kakao_tesk.type.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
