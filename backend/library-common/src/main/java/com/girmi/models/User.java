package com.girmi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String userId;
    private String userNm;
    private String userPw;
    private String userEmail;
}
