package com.parking.api.service;

import org.springframework.http.HttpMethod;

import java.util.Map;

public class ApiServiceImpl implements ApiService{
    @Override
    public String getStringXmlFromApi(int timeout, String url, HttpMethod httpMethod) {
        return null;
    }

    @Override
    public Map<String, Object> parseStringXmlToJson(String xml) {
        return null;
    }
}