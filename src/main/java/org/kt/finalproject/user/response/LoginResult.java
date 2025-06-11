package org.kt.finalproject.user.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.kt.finalproject.user.entity.User;


@Setter
@Getter
@Builder
public class LoginResult {


    private String token;
    private User user;

}
