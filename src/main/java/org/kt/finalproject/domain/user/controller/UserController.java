package org.kt.finalproject.domain.user.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kt.finalproject.domain.user.entity.User;
import org.kt.finalproject.domain.user.repository.UserRepository;
import org.kt.finalproject.domain.user.DTO.Login;
import org.kt.finalproject.domain.user.DTO.UserDto;
import org.kt.finalproject.domain.user.DTO.LoginResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.kt.finalproject.domain.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @Value("${secret}")
    private String secret;

    // user 목록
    @GetMapping
    public ResponseEntity<List<User>> getUserHandle() {
        List<User> userList = userRepository.findAll();
        return ResponseEntity.status(200).body(userList);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<User> postUserHandle(@RequestBody @Valid UserDto dto
            , BindingResult result) {
       return userService.postUserHandle(dto,result);

    }


    @PostMapping("/login")
    public ResponseEntity<LoginResult> login(@RequestBody @Valid Login login, BindingResult result) {
        return userService.login(login, result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser (@PathVariable Integer id){

        return userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser (@PathVariable Integer id,
                                      @RequestAttribute String subject,
                                      @RequestBody @Valid UserDto dto,
                                      BindingResult result) {

        return userService.updateUser(id, subject, dto, result);
    }

}