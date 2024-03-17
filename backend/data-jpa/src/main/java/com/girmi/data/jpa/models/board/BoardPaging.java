package com.girmi.data.jpa.models.board;

import lombok.Data;

import java.util.List;

@Data
public class BoardPaging {
    long totalCount;
    List<Board> boardList;
}
