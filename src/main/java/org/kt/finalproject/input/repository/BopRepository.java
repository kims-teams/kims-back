package org.kt.finalproject.input.repository;

import org.kt.finalproject.input.entity.Bop;
import org.kt.finalproject.input.entity.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BopRepository extends JpaRepository<Bop, Integer> {

    Bop findByScenario (Scenario scenario);
}
