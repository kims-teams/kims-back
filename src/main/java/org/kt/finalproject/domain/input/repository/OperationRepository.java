package org.kt.finalproject.domain.input.repository;

import org.kt.finalproject.domain.input.entity.Operation;
import org.kt.finalproject.domain.input.entity.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
    List<Operation> findByScenario(Scenario scenario);

    List<?> findByScenarioId(int scenarioId);
}
