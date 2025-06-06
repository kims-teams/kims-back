package org.kt.finalproject.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Login {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
