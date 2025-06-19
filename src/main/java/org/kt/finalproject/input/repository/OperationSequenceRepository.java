package org.kt.finalproject.input.repository;

import org.kt.finalproject.input.entity.OperationSequence;
import org.kt.finalproject.input.entity.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationSequenceRepository extends JpaRepository<OperationSequence, Integer> {
    List<OperationSequence> findByScenario(Scenario scenario);

    List<?> findByScenarioId(int scenarioId);

    //
    List<OperationSequence> findByRoutingIdOrderByOperationSeqAsc(String routingId);
}
