package org.kt.finalproject.domain.input.repository;

import org.kt.finalproject.domain.input.entity.Scenario;
import org.kt.finalproject.domain.input.entity.WorkcenterMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkcenterMapRepository extends JpaRepository<WorkcenterMap, Integer> {
    List<WorkcenterMap> findByScenario(Scenario scenario);

    List<?> findByScenarioId(int scenarioId);

    //
    List<WorkcenterMap> findByScenario_idAndOperationId(int scenarioId, String operationId);
    List<WorkcenterMap> findByScenarioAndOperationId(Scenario scenario, String operationId);

    Optional<WorkcenterMap> findByScenarioAndWorkcenterId(Scenario scenario, String workcenterId);
}
