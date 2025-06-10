package org.kt.finalproject.input.repository;

import org.kt.finalproject.input.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigRepository extends JpaRepository<Config, Integer> {
}
