package org.kt.finalproject.input.repository;

import org.kt.finalproject.input.entity.InputData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InputDataRepository extends JpaRepository<InputData, Integer> {
}
