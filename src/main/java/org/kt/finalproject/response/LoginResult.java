package org.kt.finalproject.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class LoginResult {
    private String token;
    private String businessEmail;
    private String name;
    private String position;
}
