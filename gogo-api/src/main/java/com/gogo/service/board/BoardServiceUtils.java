package com.gogo.service.board;

import com.gogo.domain.board.Board;
import com.gogo.domain.board.BoardRepository;
import com.gogo.exception.NotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class BoardServiceUtils {

    static Board findBoardById(BoardRepository boardRepository, Long boardId) {
        Board board = boardRepository.findBoardById(boardId);
        if (board == null) {
            throw new NotFoundException(String.format("해당하는 (%s)의 게시판은 존재하지 않습니다", boardId), "해당하는 게시물은 존재하지 않습니다.");
        }
        return board;
    }

    public static List<Board> getBoardsByLikeKeyword(BoardRepository boardRepository, String keyword, Long lastBoardId, int size) {
        if (lastBoardId == 0) {
            return boardRepository.findLastBoardsByLikeTitle(keyword, size);
        }
        return boardRepository.findBoardsByLikeTitle(keyword, lastBoardId, size);
    }

    public static List<Board> findBoardsOrderByIdWithPagination(BoardRepository boardRepository, Long lastBoardId, int size) {
        if (lastBoardId == 0) {
            return boardRepository.findBoardsOrderByIdDesc(size);
        }
        return boardRepository.findBoardsLessThanOrderByIdDescLimit(lastBoardId, size);
    }

}
