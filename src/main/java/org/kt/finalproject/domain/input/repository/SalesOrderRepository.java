package org.kt.finalproject.domain.input.repository;

import org.kt.finalproject.domain.input.entity.SalesOrder;
import org.kt.finalproject.domain.input.entity.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, Integer> {
    List<SalesOrder> findByScenario(Scenario scenario);

    List<SalesOrder> findByScenarioId(int scenarioId);
}
