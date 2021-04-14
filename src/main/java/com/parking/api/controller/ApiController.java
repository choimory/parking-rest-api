package com.parking.api.controller;

import com.parking.api.service.ApiService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class ApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);
    private final ApiService apiService;

    @GetMapping("/api")
    public String api(){

        return "api/list";
    }
}
