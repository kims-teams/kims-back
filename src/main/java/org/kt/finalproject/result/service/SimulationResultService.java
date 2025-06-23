package org.kt.finalproject.result.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.kt.finalproject.domain.input.entity.*;
import org.kt.finalproject.domain.input.repository.*;
import org.kt.finalproject.result.DTO.ExecutionSummaryDto;
import org.kt.finalproject.result.entity.OperationExecutionLog;
import org.kt.finalproject.result.entity.OperationToolUsage;
import org.kt.finalproject.result.entity.OperationWorkcenterUsage;
import org.kt.finalproject.result.repository.OperationExecutionLogRepository;
import org.kt.finalproject.result.repository.OperationToolUsageRepository;
import org.kt.finalproject.result.repository.OperationWorkcenterUsageRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SimulationResultService {

    private final OperationSequenceRepository operationSequenceRepository;
    private final OperationRepository operationRepository;
    private final WorkcenterMapRepository workcenterMapRepository;
    private final ToolMapRepository toolMapRepository;

    private final OperationExecutionLogRepository executionLogRepository;
    private final OperationToolUsageRepository toolUsageRepository;
    private final OperationWorkcenterUsageRepository workcenterUsageRepository;

    private final ScenarioRepository scenarioRepository;
    private final HttpServletRequest request;

    public void runSimulation(Scenario scenario) {
        List<OperationSequence> sequenceList =
                operationSequenceRepository.findByScenarioIdOrderByOperationSeqAsc(scenario.getId());

        LocalDateTime current = LocalDateTime.of(2025, 6, 23, 9, 0);
        Map<String, LocalDateTime> toolAvailable = new HashMap<>();
        Map<String, LocalDateTime> wcAvailable = new HashMap<>();

        for (OperationSequence seq : sequenceList) {
            String operationId = seq.getOperationId();

            Operation operation = operationRepository.findByOperationId(operationId)
                    .orElseThrow(() -> new RuntimeException("작업 없음: " + operationId));
            int runTime = operation.getRunTime();

            //  작업장 후보 목록 조회
            List<WorkcenterMap> wcMaps = workcenterMapRepository.findByOperationId(operationId);
            if (wcMaps.isEmpty()) continue;

            //  가장 빨리 가능한 작업장 선택
            String selectedWcId = null;
            LocalDateTime wcReady = null;
            String partId = null;
            for (WorkcenterMap wc : wcMaps) {
                String wcId = wc.getWorkcenterId();
                LocalDateTime available = wcAvailable.getOrDefault(wcId, current);
                if (selectedWcId == null || available.isBefore(wcReady)) {
                    selectedWcId = wcId;
                    wcReady = available;
                    partId = wc.getPartId(); // 같이 사용
                }
            }

            //  도구 후보 조회
            List<ToolMap> toolMaps = toolMapRepository.findByOperationId(operationId);
            String selectedToolId = null;
            LocalDateTime toolReady = null;

            for (ToolMap tool : toolMaps) {
                String toolId = tool.getToolId();
                LocalDateTime available = toolAvailable.getOrDefault(toolId, current);

                if (selectedToolId == null || toolReady == null || available.isBefore(toolReady)) {
                    selectedToolId = toolId;
                    toolReady = available;
                }
            }

            //  가장 늦은 자원 사용 가능 시간과 직렬로
            LocalDateTime startTime = Collections.max(
                    List.of(current,
                            toolReady != null ? toolReady : current,
                            wcReady != null ? wcReady : current)
            );
            LocalDateTime endTime = startTime.plusMinutes(runTime);

            // 자원 사용 시간 업데이트
            if (selectedToolId != null) toolAvailable.put(selectedToolId, endTime);
            wcAvailable.put(selectedWcId, endTime);
            current = endTime;

            //  실행 로그 저장
            OperationExecutionLog log = OperationExecutionLog.builder()
                    .scenario(scenario)
                    .operation(operation)
                    .toolId(selectedToolId)
                    .workcenterId(selectedWcId)
                    .startTime(startTime)
                    .endTime(endTime)
                    .durationMinutes(runTime)
                    .remarks(operation.getOperationName())
                    .build();
            executionLogRepository.save(log);

            //  도구 사용 이력 저장
            if (selectedToolId != null) {
                toolUsageRepository.save(OperationToolUsage.builder()
                        .executionLog(log)
                        .toolId(selectedToolId)
                        .usageTimeMinutes(runTime)
                        .remarks(null)
                        .build());
            }

            //  작업장 사용 이력 저장
            workcenterUsageRepository.save(OperationWorkcenterUsage.builder()
                    .executionLog(log)
                    .workcenterId(selectedWcId)
                    .startTime(startTime)
                    .endTime(endTime)
                    .remarks(null)
                    .build());
        }
    }



    public List<OperationExecutionLog> getExecutionLogsByScenarioId(int scenarioId) {
        return executionLogRepository.findByScenarioId(scenarioId);
    }


    public List<OperationToolUsage> getToolUsageByScenarioId(int scenarioId) {
        return toolUsageRepository.findByExecutionLog_ScenarioId(scenarioId);
    }


    public List<OperationWorkcenterUsage> getWorkcenterUsageByScenarioId(int scenarioId) {
        return workcenterUsageRepository.findByExecutionLog_ScenarioId(scenarioId);
    }



    public List<ExecutionSummaryDto> getExecutionSummaryList() {
        List<Scenario> scenarios = scenarioRepository.findAll();
        List<ExecutionSummaryDto> results = new ArrayList<>();

        //  JWT subject (email) / JWT 토큰의 subject에 로그인한 유저의 email을 넣어둠
        String userEmail = "guest";
        Object subject = request.getAttribute("subject");
        if (subject != null) {
            userEmail = subject.toString();
        }

        for (Scenario scenario : scenarios) {
            List<OperationExecutionLog> logs = executionLogRepository.findByScenarioId(scenario.getId());
            if (logs.isEmpty()) continue;

            // 시작/종료 계산
            LocalDateTime start = logs.stream()
                    .map(OperationExecutionLog::getStartTime)
                    .min(LocalDateTime::compareTo)
                    .orElse(null);

            LocalDateTime end = logs.stream()
                    .map(OperationExecutionLog::getEndTime)
                    .max(LocalDateTime::compareTo)
                    .orElse(null);

            Duration duration = Duration.between(start, end);

            String version = "Experiment_" + scenario.getId() + "_" + start.format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));

            ExecutionSummaryDto dto = ExecutionSummaryDto.builder()
                    .version(version)
                    .scenarioName(scenario.getName())
                    .status("Complete")
                    .duration(String.format("%02d:%02d:%02d",
                            duration.toHours(),
                            duration.toMinutesPart(),
                            duration.toSecondsPart()))
                    .startTime(start)
                    .endTime(end)
                    .schedule(start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .userId(userEmail)
                    .errorMessage(null)
                    .build();

            results.add(dto);
        }

        return results;
    }


}
