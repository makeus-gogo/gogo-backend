package com.gogo.service.board;

import com.gogo.domain.board.Board;
import com.gogo.domain.board.BoardRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class BoardServiceUtils {

    static Board findBoardById(BoardRepository boardRepository, Long boardId) {
        Board board = boardRepository.findBoardById(boardId);
        if (board == null) {
            throw new IllegalArgumentException(String.format("해당하는 (%s)의 게시판은 존재하지 않습니다", boardId));
        }
        return board;
    }

}
