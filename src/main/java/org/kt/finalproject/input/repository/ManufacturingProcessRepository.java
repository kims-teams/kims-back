package org.kt.finalproject.input.repository;

import org.kt.finalproject.input.entity.ManufacturingProcess;
import org.kt.finalproject.input.entity.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManufacturingProcessRepository extends JpaRepository<ManufacturingProcess, Integer> {
    List<ManufacturingProcess> findByScenario(Scenario scenario);

    List<?> findByScenarioId(int scenarioId);
}
