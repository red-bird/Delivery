package com.redbird.delivery.repositories;

import com.redbird.delivery.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
