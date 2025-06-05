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
import org.springframework.http.HttpStatus;
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

import java.time.LocalDateTime;
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

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser (@PathVariable Long id,
                                      @RequestAttribute String subject,
                                      @RequestBody @Valid UserDto dto,
                                      BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("입력값이 유효하지 않습니다.");
        }

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("유저를 찾을 수 없습니다.");
        }

        User user = optionalUser.get();

        if (!user.getEmail().equals(subject)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("본인만 수정할 수 있습니다.");
        }

        if (dto.getName() != null) {
            user.setName(dto.getName());
        }

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
        }

        if (dto.getDepartment() != null) {
            user.setDepartment(dto.getDepartment());
        }

        if (dto.getPosition() != null) {
            user.setPosition(dto.getPosition());
        }

        if (dto.getPhone() != null) {
            user.setPhone(dto.getPhone());
        }

        if (dto.getHireDate() != null) {
            user.setHireDate(dto.getHireDate());
        }

        if (dto.getStatus() != null) {
            user.setStatus(dto.getStatus());
        }

        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        return ResponseEntity.ok(user);


    }


}