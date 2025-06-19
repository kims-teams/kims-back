package org.kt.finalproject.input.repository;

import org.kt.finalproject.input.entity.SalesOrder;
import org.kt.finalproject.input.entity.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, Integer> {
    List<SalesOrder> findByScenario(Scenario scenario);

    List<SalesOrder> findByScenarioId(int scenarioId);
}
