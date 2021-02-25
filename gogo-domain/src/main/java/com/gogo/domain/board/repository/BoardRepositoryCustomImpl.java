package com.gogo.domain.board.repository;

import com.gogo.domain.board.Board;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.gogo.domain.board.QBoard.board;
import static com.gogo.domain.board.QBoardContent.boardContent;

@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Board findBoardById(Long boardId) {
        return queryFactory.selectFrom(board)
            .leftJoin(board.boardContentList, boardContent).fetchJoin()
            .where(
                board.id.eq(boardId)
            ).fetchOne();
    }

    @Override
    public List<Board> findBoardLessThanId(Long lastBoardId, int size) {
        return queryFactory.selectFrom(board)
            .where(
                board.id.lt(lastBoardId)
            )
            .orderBy(board.id.desc())
            .limit(size)
            .fetch();
    }

}
