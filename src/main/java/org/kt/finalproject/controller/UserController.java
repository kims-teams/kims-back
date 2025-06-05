package org.kt.finalproject.controller;


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

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

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
}
