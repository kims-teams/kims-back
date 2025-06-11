package org.kt.finalproject.user.repository;

import org.kt.finalproject.user.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {
}
