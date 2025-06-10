package org.kt.finalproject.input_repository;

import org.kt.finalproject.input_entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Integer> {
}
