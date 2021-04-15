package com.parking.api.service;

import org.springframework.http.HttpMethod;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ApiService {
    public String getStringXmlFromApi(int timeout, String url, HttpMethod httpMethod);
    public List<Map<String, String>> parseStringXmlToJson(String xml, String targetTagName) throws ParserConfigurationException, SAXException, IOException;
}
