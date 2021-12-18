package com.redbird.delivery.services;

import com.redbird.delivery.models.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {

    public Optional<Restaurant> findById(Long id);
    public List<Restaurant> findAll();
    public Restaurant save(Restaurant restaurant);
    public void delete(Long id);
}
