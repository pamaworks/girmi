package com.girmi.data.jpa.models.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Data
@Entity
@Table(name = "BOARD")
public class Board {
    @Id
    @Column(name = "BRD_IDX", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "brdIdx", description = "brdIdx")
    private Integer brdIdx;

    @Column(name = "BRD_CONTENT", nullable = false)
    @Schema(name = "brdContent", description = "Board Content")
    private String brdContent;

    @Column(name = "BRD_TITLE")
    @Schema(name = "brdTitle", description = "board title")
    private String brdTitle;

    @Column(name = "BRD_IMG_URL")
    @Schema(name = "brdImgUrl", description = "board image url")
    @JsonIgnore
    private String brdImgUrl;

    @Column(name = "BRD_IMG_TEXT")
    @Schema(name = "brdImgText", description = "board image text")
    @JsonIgnore
    private String brdImgText;

    @Column(name = "BRD_LINK_TEXT")
    @Schema(name = "brdLinkText", description = "board link text")
    @JsonIgnore
    private String brdLinkText;

    @Column(name = "USE_YN")
    @Schema(name = "useYn", description = "use yn", defaultValue = "Y")
    private String useYn;

    @OneToOne
    @JoinColumn(name = "BRD_TYPE", referencedColumnName = "BRD_TYPE")
    private BoardType boardType;

}
