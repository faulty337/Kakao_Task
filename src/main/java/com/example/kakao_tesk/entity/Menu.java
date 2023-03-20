package com.example.kakao_tesk.entity;

import com.example.kakao_tesk.type.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

@SQLDelete(sql = "UPDATE menu SET isDelete = true WHERE id = ?")
@Getter
@NoArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @Enumerated(EnumType.STRING)
    private Category category;

    private int price;

    @Getter
    @ColumnDefault("false")
    private boolean isDelete;

    public Menu(String name, Category category, int price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }
}
