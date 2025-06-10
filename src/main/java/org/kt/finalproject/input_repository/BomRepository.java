package org.kt.finalproject.input_repository;

import org.kt.finalproject.input_entity.Bom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BomRepository extends JpaRepository<Bom, Integer> {
}
