package org.kt.finalproject.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    private String password;

    private String department;

    private String position;

    private String phone;

    private LocalDateTime hireDate;


    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String role;

 }

