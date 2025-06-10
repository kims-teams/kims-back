package org.kt.finalproject.service;

import lombok.RequiredArgsConstructor;
import org.kt.finalproject.entity.Department;
import org.kt.finalproject.repository.DepartmentRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepo departmentRepo;

    public ResponseEntity<?> saveDepartment(@RequestBody Department department){
        department.setCreatedAt(LocalDateTime.now());
        departmentRepo.save(department);
        return ResponseEntity.status(200).body(null);
    }

    public ResponseEntity<?> updateDepartment(int id, @RequestBody Department department){
        Optional<Department> optionalDepartment = departmentRepo.findById(id);

        if (optionalDepartment.isEmpty()){
            return ResponseEntity.status(404).body(null);
        }

        Department existing = Department.builder()
                .name(department.getName())
                .description(department.getDescription())
                .updatedAt(LocalDateTime.now())
                .build();

        return ResponseEntity.status(200).body(null);
    }

    public ResponseEntity<?> deleteDepartment(int id){
        Optional<Department> optionalDepartment = departmentRepo.findById(id);

        if (optionalDepartment.isEmpty()){
            return ResponseEntity.status(404).body(null);
        }

        departmentRepo.delete(optionalDepartment.get());

        return ResponseEntity.status(200).body(null);
    }
}
