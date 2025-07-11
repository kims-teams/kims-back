package org.kt.finalproject.domain.result.repository;
import org.kt.finalproject.domain.input.entity.Scenario;
import org.kt.finalproject.domain.result.entity.OperationExecutionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationExecutionLogRepository extends JpaRepository<OperationExecutionLog, Integer> {


    List<OperationExecutionLog> findByScenarioId(int scenarioId);

    // 작업(Operation) 기준 조회
    List<OperationExecutionLog> findByScenario_idAndOperationId(int scenarioId, int operationId);
    List<OperationExecutionLog> findByScenarioAndOperationId(Scenario scenario, int operationId);

    // 설비 기준 조회 (자원간트용)
    List<OperationExecutionLog> findByWorkcenterId(String workcenterId);

    int scenario(Scenario scenario);
}
