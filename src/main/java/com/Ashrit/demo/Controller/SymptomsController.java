package com.Ashrit.demo.Controller;

import com.Ashrit.demo.Service.SymptomsAnalysisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/symptoms")
public class SymptomsController {
    private final SymptomsAnalysisService analysisService;

    public SymptomsController(SymptomsAnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @PostMapping("/recommend")
    public ResponseEntity<List<String>> recommend(@RequestBody String symptoms) {
        return ResponseEntity.ok(analysisService.getTopSpecialists(symptoms));
    }
}
