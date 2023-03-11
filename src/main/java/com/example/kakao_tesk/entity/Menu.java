package com.example.kakao_tesk.entity;

import com.example.kakao_tesk.type.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;

@Entity

@SQLDelete(sql = "UPDATE menu SET status = false WHERE id = ?")
@Getter
@NoArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Category category;

    private int price;

    @Getter
    @ColumnDefault("true")
    private boolean status;

    public Menu(String name, Category category, int price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }
}
