package org.kt.finalproject.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.kt.finalproject.entity.User;
import org.kt.finalproject.repository.UserRepository;
import org.kt.finalproject.request.Login;
import org.kt.finalproject.response.LoginResult;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final String secret = "jwt-secret-key";

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
}
