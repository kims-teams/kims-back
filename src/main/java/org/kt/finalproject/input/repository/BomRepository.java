package org.kt.finalproject.input.repository;

import org.kt.finalproject.input.entity.Bom;
import org.kt.finalproject.input.entity.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BomRepository extends JpaRepository<Bom, Integer> {
    List<Bom> findByScenario(Scenario scenario);

    List<?> findByScenarioId(int scenarioId);
}
