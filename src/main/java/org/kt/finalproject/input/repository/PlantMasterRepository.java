package org.kt.finalproject.input.repository;

import org.kt.finalproject.input.entity.PlantMaster;
import org.kt.finalproject.input.entity.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlantMasterRepository extends JpaRepository<PlantMaster, Integer> {
    List<PlantMaster> findByScenario(Scenario scenario);
}
