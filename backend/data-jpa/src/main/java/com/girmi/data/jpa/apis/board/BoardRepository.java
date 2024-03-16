package com.girmi.data.jpa.apis.board;

import com.girmi.data.jpa.common.CommonRepository;
import com.girmi.data.jpa.models.board.Board;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Integer>, CommonRepository<Board, Integer> {}
