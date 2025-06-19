package org.kt.finalproject.input.repository;

import org.kt.finalproject.input.entity.Operation;
import org.kt.finalproject.input.entity.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
    List<Operation> findByScenario(Scenario scenario);

    List<?> findByScenarioId(int scenarioId);

    //
    Optional<Operation> findByOperationId(String operationId);
}
