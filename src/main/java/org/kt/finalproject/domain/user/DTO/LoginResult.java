package org.kt.finalproject.domain.user.DTO;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.kt.finalproject.domain.user.entity.User;


@Setter
@Getter
@Builder
public class LoginResult {

    private String token;
    private User user;

}
