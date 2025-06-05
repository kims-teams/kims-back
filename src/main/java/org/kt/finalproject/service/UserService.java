package org.kt.finalproject.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    @Value("${secret}")
    private String secret;

    public ResponseEntity<LoginResult> login(@RequestBody @Valid Login login, BindingResult result) {
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

    public ResponseEntity<User> postUserHandle(@RequestBody @Valid UserDto dto
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

}
