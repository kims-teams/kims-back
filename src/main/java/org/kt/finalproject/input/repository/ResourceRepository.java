package org.kt.finalproject.input.repository;

import org.kt.finalproject.input.entity.Resource;
import org.kt.finalproject.input.entity.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Integer> {

    Resource findByScenario (Scenario scenario);
}
