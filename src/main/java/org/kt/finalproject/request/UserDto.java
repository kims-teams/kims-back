package org.kt.finalproject.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Email(message = "올바른 이메일 형식이어야 합니다.")
    @NotBlank(message = "이메일은 필수 항목입니다.")
    private String businessEmail;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,16}$",
            message = "비밀번호는 대소문자, 숫자를 포함하고 8~16자여야 합니다."
    )
    private String password;


    @Pattern(
            regexp = "^[가-힣]{2,10}$",
            message = "이름은 한글로 2~10자여야 합니다."
    )
    private String name;

    private String position;

}
