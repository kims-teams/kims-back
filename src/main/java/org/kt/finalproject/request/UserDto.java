package org.kt.finalproject.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {


    private Long id;

    @Pattern(
            regexp = "^[가-힣]{2,10}$",
            message = "이름은 한글로 2~10자여야 합니다."
    )
    private String name;


    @Email(message = "올바른 이메일 형식이어야 합니다.")
    @NotBlank(message = "이메일은 필수 항목입니다.")
    private String email;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,16}$",
            message = "비밀번호는 대소문자, 숫자를 포함하고 8~16자여야 합니다."
    )
    private String password;

    private String department;

    private String position;

    @Pattern(
            regexp = "^01[016-9]-\\d{3,4}-\\d{4}$",
            message = "휴대폰 번호는 010-0000-0000 형식이어야 합니다."
    )
    private String phone;

    private LocalDate hireDate;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
