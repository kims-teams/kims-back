package org.kt.finalproject.input.controller;

import lombok.RequiredArgsConstructor;
import org.kt.finalproject.input.entity.*;
import org.kt.finalproject.input.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/department")
@CrossOrigin
@RequiredArgsConstructor
public class ScenarioController {

    private final ScenarioRepository scenarioRepository;
    private final BopRepository bopRepository;
    private final ConfigRepository configRepository;
    private final ResourceRepository resourceRepository;
    private final TargetRepository targetRepository;

    @PostMapping("/{scenarioName}")
    public ResponseEntity<?> saveScenario(@PathVariable("scenarioName") String scenarioName){
        Scenario scenario = Scenario.builder().name(scenarioName).build();
        scenarioRepository.save(scenario);
        scenario = scenarioRepository.findByName(scenarioName);
        Bop bop = Bop.builder().scenario(scenario).build(); bopRepository.save(bop);
        Config config = Config.builder().scenario(scenario).build(); configRepository.save(config);
        Resource resource = Resource.builder().scenario(scenario).build(); resourceRepository.save(resource);
        Target target = Target.builder().scenario(scenario).build(); targetRepository.save(target);



        return ResponseEntity.status(200).body(null);
    }

}
