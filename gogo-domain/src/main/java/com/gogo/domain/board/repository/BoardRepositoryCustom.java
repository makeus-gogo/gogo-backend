package com.gogo.domain.board.repository;

import com.gogo.domain.board.Board;

import java.util.List;

public interface BoardRepositoryCustom {

    Board findBoardById(Long boardId);

    List<Board> findBoardsOrderByIdDesc(int size);

    List<Board> findBoardsLessThanOrderByIdDescLimit(Long lastBoardId, int size);

    List<Board> findBoardsByLikeTitle(String title);

}
