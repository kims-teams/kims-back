package org.kt.finalproject.result.controller;

import lombok.RequiredArgsConstructor;
import org.kt.finalproject.domain.input.entity.Scenario;
import org.kt.finalproject.domain.input.repository.ScenarioRepository;
import org.kt.finalproject.result.entity.OperationExecutionLog;
import org.kt.finalproject.result.entity.OperationToolUsage;
import org.kt.finalproject.result.entity.OperationWorkcenterUsage;
import org.kt.finalproject.result.repository.OperationExecutionLogRepository;
import org.kt.finalproject.result.repository.OperationToolUsageRepository;
import org.kt.finalproject.result.repository.OperationWorkcenterUsageRepository;
import org.kt.finalproject.result.service.SimulationResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/simulation")
@RequiredArgsConstructor
public class SimulationResultController {

    private final SimulationResultService simulationResultService;
    private final ScenarioRepository scenarioRepository;

    private final OperationExecutionLogRepository executionLogRepository;
    private final OperationToolUsageRepository toolUsageRepository;
    private final OperationWorkcenterUsageRepository workcenterUsageRepository;


    @PostMapping("/run")
    public ResponseEntity<?> runSimulation(@RequestParam String routingId,
                                           @RequestParam int scenarioId) {
        Scenario scenario = scenarioRepository.findById(scenarioId)
                .orElseThrow(() -> new RuntimeException("시나리오 없음: " + scenarioId));
        simulationResultService.runSimulation(routingId, scenario);
        return ResponseEntity.ok("시뮬레이션 완료");
    }


    @GetMapping("/results")
    public ResponseEntity<List<OperationExecutionLog>> getSimulationResults(@RequestParam int scenarioId) {
        List<OperationExecutionLog> results = executionLogRepository.findByScenarioId(scenarioId);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/tool-usage")
    public ResponseEntity<List<OperationToolUsage>> getToolUsage(@RequestParam int scenarioId) {
        List<OperationToolUsage> tools = toolUsageRepository.findByExecutionLog_ScenarioId(scenarioId);
        return ResponseEntity.ok(tools);
    }

    @GetMapping("/workcenter-usage")
    public ResponseEntity<List<OperationWorkcenterUsage>> getWorkcenterUsage(@RequestParam int scenarioId) {
        List<OperationWorkcenterUsage> wcs = workcenterUsageRepository.findByExecutionLog_ScenarioId(scenarioId);
        return ResponseEntity.ok(wcs);
    }
}
