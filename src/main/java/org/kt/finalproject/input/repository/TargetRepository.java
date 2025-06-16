package org.kt.finalproject.input.repository;

import org.kt.finalproject.input.entity.Scenario;
import org.kt.finalproject.input.entity.Target;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TargetRepository extends JpaRepository<Target, Integer> {

    Target findByScenario (Scenario scenario);
}
