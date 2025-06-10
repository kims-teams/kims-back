package org.kt.finalproject.repository;

import org.kt.finalproject.input_repository.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {
}
