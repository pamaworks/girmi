package com.girmi.data.jpa.apis.board;

import com.girmi.constants.CodeConstant;
import com.girmi.data.jpa.models.board.Board;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    public List<Board> getBoardList(String brdType) throws Exception {
        Board board = new Board();
        board.setBrdType(brdType);
        board.setUseYn("Y");

        Example<Board> example = Example.of(board);
        return boardRepository.findAll(example);

    }

    public Board getBoard(Integer brdIdx) throws Exception {
        return boardRepository.findById(brdIdx).get();
    }

    public ResponseEntity<String> saveBoard(Board board) throws Exception{
        try {
            boardRepository.save(board);
            return new ResponseEntity<>(CodeConstant.RESULT_SUCCESS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(CodeConstant.RESULT_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteBoard(Integer brdIdx) throws Exception {

        try {
            Board board = boardRepository.findById(brdIdx).get();
            board.setUseYn("N");
            boardRepository.save(board);
            return new ResponseEntity<>(CodeConstant.RESULT_SUCCESS, HttpStatus.OK);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return new ResponseEntity<>(CodeConstant.RESULT_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
