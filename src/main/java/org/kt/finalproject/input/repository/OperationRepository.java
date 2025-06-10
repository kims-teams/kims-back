package org.kt.finalproject.input.repository;

import org.kt.finalproject.input.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
}
