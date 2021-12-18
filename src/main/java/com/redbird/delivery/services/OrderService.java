package com.redbird.delivery.services;

import com.redbird.delivery.models.Order;
import com.redbird.delivery.models.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> findAllByClient(String client);
    List<Order> findAll();
    Order save(Order order);
    void delete(Long id);
}
