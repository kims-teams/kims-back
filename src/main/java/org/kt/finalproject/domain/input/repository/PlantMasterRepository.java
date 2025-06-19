package org.kt.finalproject.domain.input.repository;

import org.kt.finalproject.domain.input.entity.PlantMaster;
import org.kt.finalproject.domain.input.entity.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlantMasterRepository extends JpaRepository<PlantMaster, Integer> {
    List<PlantMaster> findByScenario(Scenario scenario);

    List<?> findByScenarioId(int scenarioId);
}
