package org.kt.finalproject.input.service;

import lombok.RequiredArgsConstructor;
import org.kt.finalproject.input.entity.Scenario;
import org.kt.finalproject.input.repository.ScenarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScenarioService {

    private final ScenarioRepository scenarioRepository;

    public ResponseEntity<?> saveScenario(String scenarioName){
        if(scenarioRepository.findByName(scenarioName) != null){
            return ResponseEntity.status(409).body("이미 존재하는 시나리오의 이름입니다");
        }

        Scenario scenario = Scenario.builder().name(scenarioName).build();
        scenarioRepository.save(scenario);

        return ResponseEntity.status(200).body(null);
    }


}
