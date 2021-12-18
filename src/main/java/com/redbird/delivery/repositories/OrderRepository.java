package com.redbird.delivery.repositories;

import com.redbird.delivery.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findAllByClient(String client);
}
