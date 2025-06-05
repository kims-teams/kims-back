package org.kt.finalproject.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kt.finalproject.entity.User;
import org.kt.finalproject.repository.UserRepository;
import org.kt.finalproject.request.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @Value("${secret}")
    private String secret;


    @GetMapping
    public ResponseEntity<?> getUserHandle() {
        List<User> userList = userRepository.findAll();
        return ResponseEntity.status(200).body(userList);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> postUserHandle (@RequestBody @Valid UserDto dto
                                                , BindingResult result) {

        if (result.hasErrors()){
            return ResponseEntity.status(400).body(null);
        }

        User user = User.builder()
                .businessEmail(dto.getBusinessEmail())
                .password(dto.getPassword())
                .name(dto.getName())
                .position(dto.getPosition()).build();

        userRepository.save(user);


        return ResponseEntity.status(201).body(user);
    }



}
