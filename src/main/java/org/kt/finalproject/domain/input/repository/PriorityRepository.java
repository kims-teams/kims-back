package org.kt.finalproject.domain.input.repository;

import org.kt.finalproject.domain.input.entity.Priority;
import org.kt.finalproject.domain.input.entity.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriorityRepository extends JpaRepository<Priority, Integer> {
    List<Priority> findByScenario(Scenario scenario);

    List<?> findByScenarioId(int scenarioId);
}
