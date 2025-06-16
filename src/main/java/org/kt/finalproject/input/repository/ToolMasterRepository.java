package org.kt.finalproject.input.repository;

import org.kt.finalproject.input.entity.Scenario;
import org.kt.finalproject.input.entity.ToolMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToolMasterRepository extends JpaRepository<ToolMaster, Integer> {
    List<ToolMaster> findByScenario(Scenario scenario);
}
