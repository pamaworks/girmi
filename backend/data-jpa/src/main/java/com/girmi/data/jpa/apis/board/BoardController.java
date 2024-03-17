package com.girmi.data.jpa.apis.board;

import com.girmi.data.jpa.models.board.Board;
import com.girmi.data.jpa.models.board.BoardPaging;
import com.girmi.data.jpa.models.board.BoardType;
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
            @Parameter(name = "brdType", required = false),
            @Parameter(name = "pageNo", example = "1"),
            @Parameter(name = "rowSize", example = "2")
    })
    @GetMapping(value = "/list")
    public BoardPaging getBoardList(
            @RequestParam(name = "brdType", required = false) String brdType,
            @RequestParam(name = "pageNo") int pageNo,
            @RequestParam(name = "rowSize") int rowSize
    ) throws Exception{
        return boardService.getBoardList(brdType, pageNo, rowSize);
    }

    @Operation(description = "board content")
    @GetMapping(value = "/{brdIdx}")
    public Board getBoard(@PathVariable(name = "brdIdx") Integer brdIdx) throws Exception {
        return boardService.getBoard(brdIdx);
    }

    @Operation(description = "save board")
    @PostMapping(value = "/save")
    public ResponseEntity<String> saveBoard(@RequestBody Board board) throws Exception {

        return boardService.saveBoard(board);
    }

    @Operation(description = "delete board")
    @DeleteMapping(value = "/{brdIdx}/delete")
    public ResponseEntity<String> deleteBoard(@PathVariable(name = "brdIdx") Integer brdIdx) throws Exception {
        return boardService.deleteBoard(brdIdx);
    }

    @Operation(description = "get board type list")
    @GetMapping(value = "/type/list")
    public List<BoardType> getBoardType() throws Exception {
        return boardService.getBoardTypeList();
    }

    @Operation(description = "insert board type")
    @PostMapping(value = "/type/save")
    public ResponseEntity<String> saveBoardType(@RequestBody BoardType boardType) throws Exception {
        return boardService.saveBoardType(boardType);
    }

    @Operation(description = "delete board type")
    @DeleteMapping(value = "/type/{brdType}/delete")
    public ResponseEntity<String> deleteBoardType(@PathVariable(name = "brdType") String boardType) throws Exception {
        return boardService.deleteBoardType(boardType);
    }

}
