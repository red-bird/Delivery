package com.redbird.delivery.controllers.pub;

import com.redbird.delivery.services.FoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class    HomeController {

    private final FoodService foodService;

    public HomeController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping()
    public String home(Model model) {
//        model.addAttribute("foodList", foodService.findAll());
        return "home";
    }
}
