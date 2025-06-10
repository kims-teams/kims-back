package org.kt.finalproject.input_repository;

import org.kt.finalproject.input_entity.InputData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InputDataRepository extends JpaRepository<InputData, Integer> {
}
