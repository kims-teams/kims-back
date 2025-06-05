package org.kt.finalproject.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.kt.finalproject.entity.User;
import org.kt.finalproject.repository.UserRepository;
import org.kt.finalproject.response.LoginResult;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final String secret = "jwt-secret-key";

    public LoginResult login(String businessEmail, String password) {
        User user = userRepository.findById(businessEmail).orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = JWT.create()
                .withIssuer("kimsteams")
                .withSubject(user.getBusinessEmail())
                .sign(Algorithm.HMAC256(secret));

        return LoginResult.builder()
                .token(token)
                .businessEmail(user.getBusinessEmail())
                .name(user.getName())
                .position(user.getPosition())
                .build();
    }
}
