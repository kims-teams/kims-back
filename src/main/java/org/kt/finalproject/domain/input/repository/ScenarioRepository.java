package org.kt.finalproject.domain.input.repository;

import org.kt.finalproject.domain.input.entity.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScenarioRepository extends JpaRepository<Scenario, Integer> {

    Scenario findByName(String name);
}
