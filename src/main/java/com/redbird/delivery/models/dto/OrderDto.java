package com.redbird.delivery.models.dto;

import com.redbird.delivery.models.Good;
import com.redbird.delivery.models.Order;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto extends Order {
    List<Good> goods;
}
