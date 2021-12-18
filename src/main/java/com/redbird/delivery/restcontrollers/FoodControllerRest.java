package com.redbird.delivery.restcontrollers;

import com.redbird.delivery.models.Food;
import com.redbird.delivery.services.FoodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/food")
public class FoodControllerRest {

    private final FoodService foodService;

    public FoodControllerRest(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping
    public List<Food> findAllFood() {
        return foodService.findAll();
    }

    @GetMapping("{id}")
    public Food findFood(@PathVariable("id") Long id) {
        Optional<Food> food = foodService.findById(id);
        return food.orElse(null);
    }

    @PostMapping()
    public Food save(@RequestBody Food food) {
        return foodService.save(food, null);
    }

    @PutMapping("{id}")
    public Food update(@RequestBody Food food, @PathVariable("id") Long id) {
        if (!food.getId().equals(id)) {
            return null;
        }
        Optional<Food> tmp = foodService.findById(id);
        if (tmp.isEmpty()) {
            return null;
        }
        Food res = tmp.get();
        res.setName(food.getName());
        res.setType(food.getType());
        res.setDescription(food.getDescription());
        res.setPrice(food.getPrice());
        return res;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        foodService.delete(id);
    }
}
