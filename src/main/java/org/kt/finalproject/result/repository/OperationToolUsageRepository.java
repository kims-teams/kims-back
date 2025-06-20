package org.kt.finalproject.result.repository;

import org.kt.finalproject.result.entity.OperationToolUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationToolUsageRepository extends JpaRepository<OperationToolUsage, Integer> {

    // 실행 ID로 도구 사용 내역 조회
    List<OperationToolUsage> findByExecutionLogId(Integer executionLogId);

    // 도구 ID로 조회
    List<OperationToolUsage> findByToolId(String toolId);


    //
    List<OperationToolUsage> findByExecutionLog_ScenarioId(int scenarioId);
}