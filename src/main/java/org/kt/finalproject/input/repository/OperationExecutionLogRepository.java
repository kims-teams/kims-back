package org.kt.finalproject.input.repository;

import org.kt.finalproject.input.entity.OperationExecutionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationExecutionLogRepository extends JpaRepository<OperationExecutionLog, Integer> {


    List<OperationExecutionLog> findByScenarioId(int scenarioId);

    // 작업(Operation) 기준 조회
    List<OperationExecutionLog> findByOperationId(int operationId);

    // 설비 기준 조회 (자원간트용)
    List<OperationExecutionLog> findByWorkcenterId(String workcenterId);

}
