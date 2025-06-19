package org.kt.finalproject.domain.input.repository;

import org.kt.finalproject.domain.input.entity.Bop;
import org.kt.finalproject.domain.input.entity.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BopRepository extends JpaRepository<Bop, Integer> {

    Bop findByScenario (Scenario scenario);
}
