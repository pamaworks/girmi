package com.girmi.data.jpa.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="GIRMI_USER_AUTHORITY")
public class UserAuthority {
    @Id
    @Schema(name = "userId", description = "userId")
    private String userId;

    @Column(name = "authority")
    @Schema(name = "authority", description = "user authority")
    private String authority;
}
