package org.kt.finalproject.result.controller;

import lombok.RequiredArgsConstructor;
import org.kt.finalproject.domain.input.entity.Scenario;
import org.kt.finalproject.domain.input.repository.ScenarioRepository;
import org.kt.finalproject.result.DTO.ExecutionManageDto;
import org.kt.finalproject.result.DTO.ExecutionResultDto;
import org.kt.finalproject.result.DTO.GanttTaskDto;
import org.kt.finalproject.result.entity.OperationExecutionLog;
import org.kt.finalproject.result.entity.OperationToolUsage;
import org.kt.finalproject.result.entity.OperationWorkcenterUsage;
import org.kt.finalproject.result.repository.OperationExecutionLogRepository;
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
    private final OperationExecutionLogRepository operationExecutionLogRepository;


    @PostMapping("/run")
    public ResponseEntity<String> runByScenario(@RequestParam int scenarioId) {
        Scenario scenario = scenarioRepository.findById(scenarioId)
                .orElseThrow(() -> new RuntimeException("시나리오 없음"));

        simulationResultService.runSimulation(scenario);
        return ResponseEntity.ok("시뮬레이션 완료");
    }


    @GetMapping("/results")
    public ResponseEntity<List<OperationExecutionLog>> getResults(@RequestParam int scenarioId) {
        return ResponseEntity.ok(
                simulationResultService.getExecutionLogsByScenarioId(scenarioId)
        );
    }

    @GetMapping("/tool-usage")
    public ResponseEntity<List<OperationToolUsage>> getToolUsage(@RequestParam int scenarioId) {
        return ResponseEntity.ok(
                simulationResultService.getToolUsageByScenarioId(scenarioId)
        );
    }

    @GetMapping("/workcenter-usage")
    public ResponseEntity<List<OperationWorkcenterUsage>> getWorkcenterUsage(@RequestParam int scenarioId) {
        return ResponseEntity.ok(
                simulationResultService.getWorkcenterUsageByScenarioId(scenarioId)
        );
    }

    @GetMapping("/execution-manage")
    public ResponseEntity<List<ExecutionManageDto>> getExecutionManage() {
        return ResponseEntity.ok(simulationResultService.getExecutionManage());
    }

    @GetMapping("/production-gantt")
    public List<GanttTaskDto> getProductionGantt(@RequestParam int scenarioId) {
        return simulationResultService.getProductionGantt(scenarioId);
    }
}
