package com.parking.api.controller;

import com.parking.api.service.ApiService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
@AllArgsConstructor
public class ApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);
    private final ApiService apiService;

    @GetMapping("/api")
    public String api(Model model, String url){
        LOGGER.info("ApiController.api() called -> {}", url);

        try{
            String xml = apiService.getStringXmlFromApi(5000, url, HttpMethod.GET);
            Map<String, Object> json = apiService.parseStringXmlToJson(xml);
        }catch (Exception e){

        }

        return "api/list";
    }
}
