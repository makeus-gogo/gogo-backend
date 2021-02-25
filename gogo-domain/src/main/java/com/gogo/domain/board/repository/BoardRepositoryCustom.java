package com.gogo.domain.board.repository;

import com.gogo.domain.board.Board;

import java.util.List;

public interface BoardRepositoryCustom {

    Board findBoardById(Long boardId);

    List<Board> findBoardLessThanId(Long lastBoardId, int size);

}
