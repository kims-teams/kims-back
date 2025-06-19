package org.kt.finalproject.domain.user.DTO;


import jakarta.validation.constraints.Pattern;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Password {

    private String oldPassword;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,16}$",
            message = "비밀번호는 대소문자, 숫자를 포함하고 8~16자여야 합니다."
    )

    private String newPassword;
}
