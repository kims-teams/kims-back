package org.kt.finalproject.domain.user.repository;

import org.kt.finalproject.domain.user.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {
}
