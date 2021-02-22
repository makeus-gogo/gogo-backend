package com.gogo.domain.board.repository;

import com.gogo.domain.board.Board;

import java.util.List;

public interface BoardRepositoryCustom {

    List<Board> findAllBoards();

    Board findBoardByUuid(String uuid);

}
