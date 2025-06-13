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
                .password(BCrypt.hashpw("0000", BCrypt.gensalt()))
                .name(dto.getName())
                .position(dto.getPosition())
                .status("재직중")
                .createdAt(LocalDateTime.now())
                .role(dto.getRole())
                .build();

        userRepository.save(user);


        return ResponseEntity.status(201).body(user);
    }

    public ResponseEntity<?> deleteUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(404).body("유저를 찾을 수 없습니다.");
        }

        userRepository.deleteById(id);

        return ResponseEntity.status(200).body("유저 삭제 완료");
    }

    public ResponseEntity<?> updateUser(Integer id,
                                        String subject,
                                        UserDto dto,
                                        BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("입력값이 유효하지 않습니다.");
        }


        // 로그인한 사용자 조회
        Optional<User> optionalLoginUser = userRepository.findByEmail(subject);
        if (optionalLoginUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인한 사용자를 찾을 수 없습니다.");
        }

        User loginUser = optionalLoginUser.get();

        // 수정 대상 사용자 조회
        Optional<User> optionalTargetUser = userRepository.findById(id);
        if (optionalTargetUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("수정할 사용자를 찾을 수 없습니다.");
        }

        User targetUser = optionalTargetUser.get();

        boolean isSelf = loginUser.getEmail().equals(targetUser.getEmail());
        boolean isAdmin = "ADMIN".equalsIgnoreCase(loginUser.getRole());
        boolean targetIsAdmin = "ADMIN".equalsIgnoreCase(targetUser.getRole());

        if (dto.getRole() != null) {
            if (!isAdmin) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("관리자만 다른 사용자의 권한을 수정할 수 있습니다.");
            }

            if (targetIsAdmin) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("다른 관리자의 권한은 수정할 수 없습니다.");
            }

            targetUser.setRole(dto.getRole());
        }

        // 일반 유저일땐 다른사람 수정 불가
        if (!isSelf && !isAdmin) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("다른 사용자의 정보를 수정할 수 없습니다.");
        }

        // USER 더라도 비번 폰번 수정허용
        if (isSelf) {
            if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
                targetUser.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
            }

            if (dto.getPhone() != null) {
                targetUser.setPhone(dto.getPhone());
            }
        }

        // 관리자는 타겟유저 필드 수정 가능
        if (isAdmin) {
            if (dto.getName() != null) targetUser.setName(dto.getName());
            if (dto.getDepartment() != null) targetUser.setDepartment(dto.getDepartment());
            if (dto.getPosition() != null) targetUser.setPosition(dto.getPosition());
            if (dto.getHireDate() != null) targetUser.setHireDate(dto.getHireDate());
            if (dto.getStatus() != null) targetUser.setStatus(dto.getStatus());
        }

        targetUser.setUpdatedAt(LocalDateTime.now());
        userRepository.save(targetUser);

        return ResponseEntity.ok(targetUser);
    }

}