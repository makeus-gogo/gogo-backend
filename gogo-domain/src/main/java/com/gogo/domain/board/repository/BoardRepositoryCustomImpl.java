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
    public List<Board> findAllBoards() {
        return queryFactory.selectFrom(board)
            .leftJoin(board.boardContentList, boardContent).fetchJoin()
            .fetch();
    }

    @Override
    public Board findBoardByUuid(String uuid) {
        return queryFactory.selectFrom(board)
            .leftJoin(board.boardContentList, boardContent).fetchJoin()
            .where(
                board.uuid.uuid.eq(uuid)
            ).fetchOne();
    }

}
