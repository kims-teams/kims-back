package org.kt.finalproject.user.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.kt.finalproject.user.entity.User;
import org.kt.finalproject.user.repository.UserRepository;
import org.kt.finalproject.user.request.Login;
import org.kt.finalproject.user.request.UserDto;
import org.kt.finalproject.user.response.LoginResult;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    @Value("${secret}")
    private String secret;

    public ResponseEntity<LoginResult> login(Login login, BindingResult result) {
        //User user = userRepository.findById(businessEmail).orElseThrow(() -> new RuntimeException("Invalid email or password"));
        if (result.hasErrors()) {
            return ResponseEntity.status(400).body(null);
        }

        Optional<User> user = userRepository.findByEmail(login.getEmail());
        if (user.isEmpty() || !BCrypt.checkpw(login.getPassword(), user.get().getPassword())) {
            return ResponseEntity.status(401).body(null);
        }


//        if (!BCrypt.checkpw(password, user.getPassword())) {
//            throw new RuntimeException("Invalid password");
//        }

        String token = JWT.create()
                .withIssuer("kimsteams")
                .withSubject(user.get().getEmail())
                .sign(Algorithm.HMAC256(secret));

        LoginResult loginResult = LoginResult.builder()
                .token(token)
                .user(user.get()).build();

        return ResponseEntity.status(200).body(loginResult);
    }

    public ResponseEntity<User> postUserHandle(UserDto dto
            , BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.status(400).body(null);
        }

        User user = User.builder()
                .email(dto.getEmail())
                .password(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()))
                .name(dto.getName())
                .position(dto.getPosition())
                .password("0000")
                .status("재직중")
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);


        return ResponseEntity.status(201).body(user);
    }

    public ResponseEntity<?> deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(404).body("유저를 찾을 수 없습니다.");
        }

        userRepository.deleteById(id);

        return ResponseEntity.status(200).body("유저 삭제 완료");
    }

    public ResponseEntity<?> updateUser(Long id,
                                        String subject,
                                        UserDto dto,
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
