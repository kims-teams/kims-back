package org.kt.finalproject.domain.input.repository;

import org.kt.finalproject.domain.input.entity.Scenario;
import org.kt.finalproject.domain.input.entity.ToolMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ToolMapRepository extends JpaRepository<ToolMap, Integer> {
    List<ToolMap> findByScenario(Scenario scenario);

    List<?> findByScenarioId(int scenarioId);

    //
    List<ToolMap> findByPartId(String partId);

    // 공정 기준 도구 조회
    List<ToolMap> findByScenario_IdAndOperationId(int scenarioId, String operationId);
    List<ToolMap> findByScenarioAndOperationId(Scenario scenario, String operationId);

    Optional<ToolMap> findByScenarioAndToolIdAndOperationId(Scenario scenario, String toolId, String operationId);

}
