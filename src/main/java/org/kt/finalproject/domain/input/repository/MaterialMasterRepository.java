package org.kt.finalproject.domain.input.repository;

import org.kt.finalproject.domain.input.entity.MaterialMaster;
import org.kt.finalproject.domain.input.entity.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialMasterRepository extends JpaRepository<MaterialMaster, Integer> {
    List<MaterialMaster> findByScenario(Scenario scenario);

    List<?> findByScenarioId(int scenarioId);
}
