package org.kt.finalproject.result.repository;

import org.kt.finalproject.result.DTO.OperationWorkCenterUsageDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationWorkCenterUsageRepository extends JpaRepository<OperationWorkCenterUsageDto, Integer> {

    // 실행 로그 기준 조회
    List<OperationWorkCenterUsageDto> findByExecutionLogId(Integer executionLogId);

    // 설비 ID 기준 조회 (자원간트용)
    List<OperationWorkCenterUsageDto> findByWorkCenterId(String workCenterId);

    //
    List<OperationWorkCenterUsageDto> findByExecutionLog_ScenarioId(int scenarioId);
}