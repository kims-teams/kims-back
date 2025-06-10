package org.kt.finalproject.input_repository;

import org.kt.finalproject.input_entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
}
