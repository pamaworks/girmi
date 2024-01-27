package com.girmi.data.jpa.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="GIRMI_USER")
public class User {
    @Id
    @Column(name = "userId", unique = true)
    @Schema(name = "userId", description = "userId")
    private String userId;

    @Column(name = "userPw")
    @Schema(name = "userPw", description = "user passwd")
    private String userPw;

    @Column(name = "userNm")
    @Schema(name = "userNm", description = "user name")
    private String userNm;

    @Column(name = "userEmail")
    @Schema(name = "userEmail", description = "user email")
    private String userEmail;
}
