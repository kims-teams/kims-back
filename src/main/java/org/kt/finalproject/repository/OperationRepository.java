package org.kt.finalproject.repository;

import org.kt.finalproject.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
}
