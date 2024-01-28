package com.girmi.jwt.models;

import com.girmi.models.User;
import lombok.Data;

@Data
public class SignIn {

    private User user;

    private String token;
    public SignIn(User user, String token) {
        this.user = user;
        this.token = token;
    }
}
