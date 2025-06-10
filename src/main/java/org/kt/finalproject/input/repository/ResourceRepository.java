package org.kt.finalproject.input.repository;

import org.kt.finalproject.input.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Integer> {
}
