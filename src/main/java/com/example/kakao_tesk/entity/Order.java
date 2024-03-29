package com.example.kakao_tesk.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
public class Order extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer price;

    @ManyToOne
    private User user;

    @ManyToOne
    private Menu menu;

    public Order(Integer price, User user, Menu menu) {
        this.price = price;
        this.user = user;
        this.menu = menu;
    }
}
