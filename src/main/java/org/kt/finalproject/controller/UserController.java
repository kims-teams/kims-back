package org.kt.finalproject.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kt.finalproject.request.Login;
import org.kt.finalproject.response.LoginResult;
import org.kt.finalproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<User> postUserHandle (@RequestBody @Valid UserDto dto
                                                , BindingResult result) {

        if (result.hasErrors()){
            return ResponseEntity.status(400).body(null);
        }

        User user = User.builder()
                .businessEmail(dto.getBusinessEmail())
                .password(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()))
                .name(dto.getName())
                .position(dto.getPosition()).build();

        userRepository.save(user);


        return ResponseEntity.status(201).body(user);
    }
  
    @PostMapping("/login")
    public ResponseEntity<LoginResult> login(@RequestBody @Valid Login login, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            LoginResult loginResult = userService.login(login.getBusinessEmail(), login.getPassword());
            return ResponseEntity.ok(loginResult);
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<LoginResult> verifyHandle(@RequestBody @Valid Login login, BindingResult result) {

        System.out.println("🔔 /verify 요청 도착");

        if (result.hasErrors()) {
            return ResponseEntity.status(400).body(null);
        }

        Optional<User> user = userRepository.findByBusinessEmail(login.getBusinessEmail());
        if (user.isEmpty() || !BCrypt.checkpw(login.getPassword(), user.get().getPassword())) {
            return ResponseEntity.status(401).body(null);
        }

        System.out.println("비밀번호 일치 여부: " + BCrypt.checkpw(login.getPassword(), user.get().getPassword()));


        String token =
                JWT.create().withIssuer("kimsteams")
                        .withSubject(user.get().getBusinessEmail())
                        .sign(Algorithm.HMAC256(secret));

        LoginResult loginResult = LoginResult.builder()
                .token(token)
                .user(user.get()).build();

        return ResponseEntity.status(200).body(loginResult);

    }
  

