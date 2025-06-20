package org.kt.finalproject.result.repository;

import org.kt.finalproject.result.entity.OperationMaterialUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationMaterialUsageRepository extends JpaRepository<OperationMaterialUsage, Integer> {

    // 실행 로그 기준으로 자재 사용 조회
    List<OperationMaterialUsage> findByExecutionLogId(Integer executionLogId);

    // 자재 ID 기준으로 조회 (MaterialMaster.partId)
    List<OperationMaterialUsage> findByMaterialId(String materialId);
}