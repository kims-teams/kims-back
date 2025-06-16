package org.kt.finalproject.input.controller;

import lombok.RequiredArgsConstructor;
import org.kt.finalproject.input.entity.*;
import org.kt.finalproject.input.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
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
        Bop bop = Bop.builder().scenario(scenario).build(); bopRepository.save(bop); bop = bopRepository.findByScenario(scenario);
        Config config = Config.builder().scenario(scenario).build(); configRepository.save(config); config = configRepository.findByScenario(scenario);
        Resource resource = Resource.builder().scenario(scenario).build(); resourceRepository.save(resource); resource = resourceRepository.findByScenario(scenario);
        Target target = Target.builder().scenario(scenario).build(); targetRepository.save(target); target = targetRepository.findByScenario(scenario);

        Map<String,Object> map = new HashMap<>();
        map.put("scenario",scenario);
        map.put("bop",bop);
        map.put("config",config);
        map.put("resource",resource);
        map.put("target",target);

        return ResponseEntity.status(200).body(map);
    }


}
