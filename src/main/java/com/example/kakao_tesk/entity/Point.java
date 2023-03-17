package com.example.kakao_tesk.entity;

import com.example.kakao_tesk.type.PointType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
