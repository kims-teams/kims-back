package org.kt.finalproject.domain.input.repository;

import org.kt.finalproject.domain.input.entity.Scenario;
import org.kt.finalproject.domain.input.entity.WorkcenterMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkcenterMasterRepository extends JpaRepository<WorkcenterMaster, Integer> {
    List<WorkcenterMaster> findByScenario(Scenario scenario);

    List<?> findByScenarioId(int scenarioId);
}
