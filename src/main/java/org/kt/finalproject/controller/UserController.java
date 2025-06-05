package org.kt.finalproject.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kt.finalproject.entity.User;
import org.kt.finalproject.repository.UserRepository;
import org.kt.finalproject.request.Login;
import org.kt.finalproject.request.UserDto;
import org.kt.finalproject.response.LoginResult;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.kt.finalproject.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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
    public ResponseEntity<?> deleteUser (@PathVariable Long id){
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()) {
            return ResponseEntity.status(404).body("유저를 찾을 수 없습니다.");
        }

        userRepository.deleteById(id);

        return ResponseEntity.status(200).body("유저 삭제 완료");
    }
}