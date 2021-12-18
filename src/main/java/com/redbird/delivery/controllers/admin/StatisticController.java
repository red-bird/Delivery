package com.redbird.delivery.controllers.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/statistic")
@PreAuthorize("hasAuthority('permission:admin')")
public class StatisticController {

    @GetMapping
    public String getStatistic(){
        return "statistic";
    }

}
