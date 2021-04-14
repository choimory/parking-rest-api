package com.parking.api.service;

import org.springframework.http.HttpMethod;

import java.util.Map;

public interface ApiService {
    public String getStringXmlFromApi(int timeout, String url, HttpMethod httpMethod);
    public Map<String, Object> parseStringXmlToJson(String xml);
}
