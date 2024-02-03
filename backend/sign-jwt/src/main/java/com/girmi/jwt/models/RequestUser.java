package com.girmi.jwt.models;

import lombok.Data;

@Data
public class RequestUser {
    private String userId;
    private String userPw;
}
