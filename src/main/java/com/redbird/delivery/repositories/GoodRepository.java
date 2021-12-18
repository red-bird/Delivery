package com.redbird.delivery.repositories;

import com.redbird.delivery.models.Good;
import com.redbird.delivery.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodRepository extends JpaRepository<Good, Long> {
    List<Good> findAllByClient(String client);
    Good findFirstByClientAndName(String client, String name);
    List<Good> findAllByOrder(Order order);
}
