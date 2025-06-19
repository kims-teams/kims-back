package org.kt.finalproject.input.repository;

import org.kt.finalproject.input.entity.Scenario;
import org.kt.finalproject.input.entity.WorkcenterMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkcenterMapRepository extends JpaRepository<WorkcenterMap, Integer> {
    List<WorkcenterMap> findByScenario(Scenario scenario);

    List<?> findByScenarioId(int scenarioId);

    //
    List<WorkcenterMap> findByOperationId(String operationId);
}
