package com.girmi.data.jpa.models.board;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "BOARD_TYPE")
public class BoardType {
    @Id
    @Column(name = "BRD_TYPE", unique = true)
    @Schema(name = "brdType", description = "brdType")
    private String brdType;

    @Column(name = "BRD_NM")
    @Schema(name = "brdNm", description = "brdNm")
    private String brdNm;
}
