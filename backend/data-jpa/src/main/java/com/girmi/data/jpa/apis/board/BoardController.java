package com.girmi.data.jpa.apis.board;

import com.girmi.data.jpa.models.board.Board;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @Operation(description = "board list" ,parameters = {
            @Parameter(name = "brdType", required = false)
    })
    @GetMapping(value = "/list")
    public List<Board> getBoardList(
            @RequestParam(name = "brdType", required = false) String brdType
    ) throws Exception{
        return boardService.getBoardList(brdType);
    }

    @Operation(description = "board content")
    @PostMapping(value = "/{brdIdx}")
    public Board getBoard(@PathVariable(name = "brdIdx") Integer brdIdx) throws Exception {
        return boardService.getBoard(brdIdx);
    }


    @Operation(description = "save board")
    @PostMapping(value = "/save")
    public ResponseEntity<String> saveBoard(Board board) throws Exception {

        return boardService.saveBoard(board);
    }

    @Operation(description = "delete board")
    @DeleteMapping(value = "/{brdIdx}/delete")
    public ResponseEntity<String> deleteBoard(@PathVariable(name = "brdIdx") Integer brdIdx) throws Exception {
        return boardService.deleteBoard(brdIdx);
    }

}
