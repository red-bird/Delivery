package com.redbird.delivery.restcontrollers;

import com.redbird.delivery.models.Restaurant;
import com.redbird.delivery.services.RestaurantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/restaurants")
public class RestaurantControllerRest {

    private final RestaurantService restaurantService;

    public RestaurantControllerRest(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<Restaurant> findAllRestaurants() {
        return restaurantService.findAll();
    }

    @GetMapping("{id}")
    public Restaurant findRestaurant(@PathVariable("id") Long id) {
        Optional<Restaurant> restaurant = restaurantService.findById(id);
        return restaurant.orElse(null);
    }

    @PostMapping
    public Restaurant save(@RequestBody Restaurant restaurant) {
        return restaurantService.save(restaurant);
    }

    @PutMapping("{id}")
    public Restaurant update(@RequestBody Restaurant restaurant, @PathVariable("id") Long id) {
        if (!restaurant.getId().equals(id)) {
            return null;
        }
        Optional<Restaurant> tmp = restaurantService.findById(id);
        if (tmp.isEmpty()) {
            return null;
        }
        Restaurant res = tmp.get();
        res.setAddress(restaurant.getAddress());
        res.setPhone(restaurant.getPhone());
        res.setWorktime(restaurant.getWorktime());
        res.setDescription(restaurant.getDescription());
        return res;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        restaurantService.delete(id);
    }
}
