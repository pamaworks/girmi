package com.girmi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserVO {
    private String userId;
    private String userPw;
    private String userNm;
}
