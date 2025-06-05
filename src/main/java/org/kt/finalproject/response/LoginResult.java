package org.kt.finalproject.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.kt.finalproject.entity.User;


@Setter
@Getter
@Builder
public class LoginResult {


    private String token;
    private User user;

}
