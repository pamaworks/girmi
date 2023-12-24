package com.girmi.models;

import lombok.Data;

@Data
public class LoginVO {
    private UserVO user;
    private String token;
    public LoginVO(UserVO user, String token) {
        this.user = user;
        this.token = token;
    }

}
