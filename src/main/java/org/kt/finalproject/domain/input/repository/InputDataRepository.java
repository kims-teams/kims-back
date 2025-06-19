package org.kt.finalproject.domain.input.repository;

import org.kt.finalproject.domain.input.entity.InputData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InputDataRepository extends JpaRepository<InputData, Integer> {
}
