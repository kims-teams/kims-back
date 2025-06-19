package org.kt.finalproject.result.service;

import lombok.RequiredArgsConstructor;
import org.kt.finalproject.input.entity.*;
import org.kt.finalproject.input.repository.OperationRepository;
import org.kt.finalproject.input.repository.OperationSequenceRepository;
import org.kt.finalproject.input.repository.ToolMapRepository;
import org.kt.finalproject.input.repository.WorkcenterMapRepository;
import org.kt.finalproject.result.entity.OperationExecutionLog;
import org.kt.finalproject.result.entity.OperationToolUsage;
import org.kt.finalproject.result.entity.OperationWorkcenterUsage;
import org.kt.finalproject.result.repository.OperationExecutionLogRepository;
import org.kt.finalproject.result.repository.OperationToolUsageRepository;
import org.kt.finalproject.result.repository.OperationWorkcenterUsageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void runSimulation(String routingId, Scenario scenario) {
        List<OperationSequence> sequenceList =
                operationSequenceRepository.findByRoutingIdOrderByOperationSeqAsc(routingId);

        Map<String, LocalDateTime> toolAvailable = new HashMap<>();
        Map<String, LocalDateTime> wcAvailable = new HashMap<>();
        LocalDateTime baseTime = LocalDateTime.of(2025, 6, 18, 9, 0);

        for (OperationSequence seq : sequenceList) {
            String operationId = seq.getOperationId();

            Operation operation = operationRepository.findByOperationId(operationId)
                    .orElseThrow(() -> new RuntimeException("작업 없음: " + operationId));
            int runTime = operation.getRunTime();

            // 1. 작업에 연결된 workcenterMap → partId 추출
            List<WorkcenterMap> wcMaps = workcenterMapRepository.findByOperationId(operationId);
            if (wcMaps.isEmpty()) continue;
            String partId = wcMaps.get(0).getPartId();

            // 2. 해당 작업에 가능한 workcenter 중 가장 빨리 가능한 것 선택
            String selectedWcId = null;
            LocalDateTime wcReady = null;
            for (WorkcenterMap wc : wcMaps) {
                String wcId = wc.getWorkcenterId();
                LocalDateTime available = wcAvailable.getOrDefault(wcId, baseTime);
                if (selectedWcId == null || available.isBefore(wcReady)) {
                    selectedWcId = wcId;
                    wcReady = available;
                }
            }

            // 3. partId에 매핑된 도구 중 가장 빨리 사용 가능한 것 선택
            List<ToolMap> toolMaps = toolMapRepository.findByPartId(partId);
            String selectedToolId = null;
            LocalDateTime toolReady = null;
            for (ToolMap tool : toolMaps) {
                String tId = tool.getToolId();
                LocalDateTime available = toolAvailable.getOrDefault(tId, baseTime);
                if (selectedToolId == null || available.isBefore(toolReady)) {
                    selectedToolId = tId;
                    toolReady = available;
                }
            }

            // 4. 시작시간은 두 자원 중 늦은 시간
            LocalDateTime startTime = (toolReady != null && toolReady.isAfter(wcReady)) ? toolReady : wcReady;
            LocalDateTime endTime = startTime.plusMinutes(runTime);

            // 5. 실행 로그 저장
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

            // 6. 도구 사용 기록
            if (selectedToolId != null) {
                toolUsageRepository.save(OperationToolUsage.builder()
                        .executionLog(log)
                        .toolId(selectedToolId)
                        .usageTimeMinutes(runTime)
                        .remarks(null)
                        .build());
                toolAvailable.put(selectedToolId, endTime);
            }

            // 7. 작업장 사용 기록
            if (selectedWcId != null) {
                workcenterUsageRepository.save(OperationWorkcenterUsage.builder()
                        .executionLog(log)
                        .workcenterId(selectedWcId)
                        .startTime(startTime)
                        .endTime(endTime)
                        .remarks(null)
                        .build());
                wcAvailable.put(selectedWcId, endTime);
            }
        }
    }
}
