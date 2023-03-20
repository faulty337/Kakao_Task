package com.example.kakao_tesk.repository;

import com.example.kakao_tesk.dto.response.RankResponse;
import com.example.kakao_tesk.entity.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select "+
            "new com.example.kakao_tesk.dto.response.RankResponse(order.menu.name, order.menu.price, order.menu.category, count(*))"+
            "from Order order "+
            "group by order.menu "+
            "order by count(*) DESC"

    )
    List<RankResponse> findTop3ByCreatedAtAfter(LocalDateTime day, PageRequest pageRequest);
}
