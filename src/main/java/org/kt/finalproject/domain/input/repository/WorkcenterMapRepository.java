package org.kt.finalproject.domain.input.repository;

import org.kt.finalproject.domain.input.entity.Scenario;
import org.kt.finalproject.domain.input.entity.WorkcenterMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkcenterMapRepository extends JpaRepository<WorkcenterMap, Integer> {
    List<WorkcenterMap> findByScenario(Scenario scenario);

    List<?> findByScenarioId(int scenarioId);
}
