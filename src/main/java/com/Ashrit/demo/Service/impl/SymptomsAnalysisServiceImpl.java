package com.Ashrit.demo.Service.impl;

import com.Ashrit.demo.Service.SymptomsAnalysisService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class SymptomsAnalysisServiceImpl implements SymptomsAnalysisService {

    private final RestTemplate restTemplate;

    public SymptomsAnalysisServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public List<String> getTopSpecialists(String symptomsText) {
        String url = "http://localhost:5000/predict"; // Replace with your Flask server

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("symptoms", symptomsText);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
        return (List<String>) response.getBody().get("top_specialists");
    }
}
