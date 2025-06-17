package org.kt.finalproject.input.controller;

import lombok.RequiredArgsConstructor;
import org.kt.finalproject.input.entity.*;
import org.kt.finalproject.input.repository.*;
import org.kt.finalproject.input.service.ScenarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/scenario")
@CrossOrigin
@RequiredArgsConstructor
public class ScenarioController {

    private final ScenarioService scenarioService;
    private final ScenarioRepository scenarioRepository;

    @PostMapping("/{scenarioName}")
    public ResponseEntity<?> saveScenario(@PathVariable("scenarioName") String scenarioName) {
        System.out.println("asd");
        return scenarioService.saveScenario(scenarioName);
    }

    @GetMapping("/{scenarioName}")
    public ResponseEntity<?> getScenarioData(@PathVariable("scenarioName") String scenarioName) {
        return scenarioService.getScenario(scenarioName);
    }

    @GetMapping()
    public ResponseEntity<?> getScenario(){
        return ResponseEntity.status(200).body(scenarioRepository.findAll());
    }


}
