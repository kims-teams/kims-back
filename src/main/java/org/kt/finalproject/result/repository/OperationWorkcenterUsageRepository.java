package org.kt.finalproject.result.repository;

import org.kt.finalproject.result.entity.OperationWorkcenterUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationWorkcenterUsageRepository extends JpaRepository<OperationWorkcenterUsage, Integer> {

    // 실행 로그 기준 조회
    List<OperationWorkcenterUsage> findByExecutionLog_Id(Integer executionLogId);

    // 설비 ID 기준 조회 (자원간트용)
    List<OperationWorkcenterUsage> findByWorkcenterId(String workcenterId);
}