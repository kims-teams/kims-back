package org.kt.finalproject.input.repository;

import org.kt.finalproject.input.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority, Integer> {
}
