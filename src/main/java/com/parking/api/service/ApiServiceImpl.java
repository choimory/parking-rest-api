package com.parking.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiServiceImpl implements ApiService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiServiceImpl.class);

    @Override
    public String getStringXmlFromApi(int timeout, String url, HttpMethod httpMethod) {
        LOGGER.info("ApiServiceImpl.getStringXmlFromApi() called -> {}", timeout, url, httpMethod);

        /*타임아웃 설정*/
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(timeout);
        factory.setReadTimeout(timeout);
        RestTemplate restTemplate = new RestTemplate(factory);

        /*Header 설정*/
        HttpHeaders headers = new HttpHeaders();

        /*HttpEntity 생성*/
        HttpEntity<?> entity = new HttpEntity<>(headers);

        /*API 호출*/
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, httpMethod, entity, String.class);
        String xml = responseEntity.getBody();

        return xml;
    }

    @Override
    public List<Map<String, String>> parseStringXmlToJson(String xml, String targetTagName) throws ParserConfigurationException, SAXException, IOException {
        /*XML 읽어들여 Document 객체 생성*/
        InputSource inputSource = new InputSource(new StringReader(xml));
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputSource);

        /*파싱*/
        List<Map<String, String>> resultList = xmlParsingByDocument(xml, document, targetTagName);

        return resultList;
    }

    private List<Map<String, String>> xmlParsingByDocument(String xml, Document document, String targetTagName){
        List<Map<String, String>> resultList = new ArrayList<>();

        /*타겟 태그들*/
        NodeList nodeList = document.getElementsByTagName(targetTagName);
        for(int i=0; i< nodeList.getLength(); i++){
            Map<String, String> nodeMap = new HashMap<>();
            Node node = nodeList.item(i);

            /*타겟 태그의 하위 태그들*/
            NodeList childNodeList = node.getChildNodes();
            for(int j=0; j<childNodeList.getLength(); j++){
                Node childNode = childNodeList.item(j);
                if(childNode.getNodeType() == Node.ELEMENT_NODE){
                    nodeMap.put(childNode.getNodeName(), childNode.getTextContent());
                }
            }
            resultList.add(nodeMap);
        }

        return resultList;
    }
}
