package com.girmi.jwt.models;

import lombok.Data;

@Data
public class UserAuthority {
    private String userId;
    private String authority;
}
