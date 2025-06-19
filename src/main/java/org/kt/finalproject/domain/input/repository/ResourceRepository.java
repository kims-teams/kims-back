package org.kt.finalproject.domain.input.repository;

import org.kt.finalproject.domain.input.entity.Resource;
import org.kt.finalproject.domain.input.entity.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Integer> {

    Resource findByScenario (Scenario scenario);
}
