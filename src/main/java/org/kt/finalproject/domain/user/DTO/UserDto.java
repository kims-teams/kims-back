package org.kt.finalproject.domain.user.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {


    private Integer id;

    @Pattern(
            regexp = "^[가-힣]{2,10}$",
            message = "이름은 한글로 2~10자여야 합니다."
    )
    private String name;


    @Email(message = "올바른 이메일 형식이어야 합니다.")
    private String email;

    private String password;

    private String department;

    private String position;

    @Pattern(
            regexp = "^01[016-9]-\\d{3,4}-\\d{4}$",
            message = "휴대폰 번호는 010-0000-0000 형식이어야 합니다."
    )
    private String phone;

    private LocalDateTime hireDate;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String role;


}
