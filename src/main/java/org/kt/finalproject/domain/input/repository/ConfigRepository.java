package org.kt.finalproject.domain.input.repository;

import org.kt.finalproject.domain.input.entity.Config;
import org.kt.finalproject.domain.input.entity.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigRepository extends JpaRepository<Config, Integer> {

    Config findByScenario (Scenario scenario);
}
