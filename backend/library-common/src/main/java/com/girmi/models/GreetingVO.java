package com.girmi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="GREETING")
public class GreetingVO {
    @Id
    @Column(name = "id", unique = true)
    @Schema(name = "id", description = "id")
    private String id;

    @Column(name = "text")
    @Schema(name = "text", description = "text")
    private String text;

    @Column(name = "code")
    @Schema(name = "code", description = "text")
    private String code;

}
