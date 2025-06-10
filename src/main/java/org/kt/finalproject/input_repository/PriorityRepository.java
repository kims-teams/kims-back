package org.kt.finalproject.input_repository;

import org.kt.finalproject.input_entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority, Integer> {
}
