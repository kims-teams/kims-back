package org.kt.finalproject.controller;

import lombok.RequiredArgsConstructor;
import org.kt.finalproject.entity.Department;
import org.kt.finalproject.repository.DepartmentRepo;
import org.kt.finalproject.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
@CrossOrigin
@RequiredArgsConstructor
public class DepartmentCont {

    private final DepartmentRepo departmentRepo;
    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<?> findDepartment(){
        return ResponseEntity.status(200).body(departmentRepo.findAll());
    }

    @PostMapping
    public ResponseEntity<?> saveDepartment(@RequestBody Department department){
        return departmentService.saveDepartment(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable int id, @RequestBody Department department){
        return departmentService.updateDepartment(id, department);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable int id){
        return departmentService.deleteDepartment(id);
    }


}
