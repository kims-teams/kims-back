//package org.kt.finalproject.result.repository;
//
//import org.kt.finalproject.result.DTO.OperationToolUsageDto;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface OperationToolUsageRepository extends JpaRepository<OperationToolUsageDto, Integer> {
//
//    // 실행 ID로 도구 사용 내역 조회
//    List<OperationToolUsageDto> findByExecutionLogId(Integer executionLogId);
//
//    // 도구 ID로 조회
//    List<OperationToolUsageDto> findByToolId(String toolId);
//
//
//    //
//    List<OperationToolUsageDto> findByExecutionLog_ScenarioId(int scenarioId);
//}