package com.redbird.delivery.servicesImpl;

import com.redbird.delivery.models.Restaurant;
import com.redbird.delivery.repositories.RestaurantRepository;
import com.redbird.delivery.services.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    @Override
    public List<Restaurant> findAll() {
        List<Restaurant> all = restaurantRepository.findAll();
        log.info("get all restaurants " + all);
        return all;
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        log.info("save() input: " + restaurant);
        Restaurant save = restaurantRepository.save(restaurant);
        log.info("save() output: " + save);
        return save;
    }

    @Override
    public void delete(Long id) {
        log.info("delete() input: " + id);
        restaurantRepository.deleteById(id);
        log.info("delete() " + id + " success");
    }
}
