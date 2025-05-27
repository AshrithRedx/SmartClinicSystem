package com.Ashrit.demo.Service.impl;

import com.Ashrit.demo.Service.SymptomsAnalysisService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SymptomsAnalysisServiceImpl implements SymptomsAnalysisService {

    private final RestTemplate restTemplate;

    public SymptomsAnalysisServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public List<String> getTopSpecialists(String symptomsJson) {
        RestTemplate restTemplate = new RestTemplate();
    
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(symptomsJson, headers);
    
        ResponseEntity<Map> response = restTemplate.postForEntity(
            "http://localhost:5000/predict",
            entity,
            Map.class
        );
    
        List<String> recommendedSpecialists = new ArrayList<>();
        List<Map<String, Object>> predictions = (List<Map<String, Object>>) response.getBody().get("top_predictions");
    
        for (Map<String, Object> prediction : predictions) {
            List<String> specialists = (List<String>) prediction.get("specialists");
            if (specialists != null && !specialists.isEmpty()) {
                recommendedSpecialists.addAll(specialists);
            }
        }
    
        return recommendedSpecialists.stream()
    .distinct()
    .collect(Collectors.toList());

    }
    
}
