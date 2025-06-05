package org.kt.finalproject.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Login {


    @NotBlank
    private String businessEmail;
    @NotBlank
    private String password;
}
