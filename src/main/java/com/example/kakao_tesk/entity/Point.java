package com.example.kakao_tesk.entity;

import com.example.kakao_tesk.type.PointType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Point extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long amount;

    private PointType status;

    @ManyToOne
    private User user;


    public Point(Long amount, PointType status, User user) {
        this.amount = amount;
        this.status = status;
        this.user = user;
    }
}
