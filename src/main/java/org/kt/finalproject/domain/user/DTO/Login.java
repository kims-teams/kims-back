package org.kt.finalproject.domain.user.DTO;


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
