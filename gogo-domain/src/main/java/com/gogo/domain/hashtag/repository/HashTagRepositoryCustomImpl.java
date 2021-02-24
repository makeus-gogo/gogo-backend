package com.gogo.domain.hashtag.repository;

import com.gogo.domain.hashtag.HashTag;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.gogo.domain.hashtag.QHashTag.hashTag;

@RequiredArgsConstructor
public class HashTagRepositoryCustomImpl implements HashTagRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<HashTag> getAllHashTagByBoardId(Long boardId) {
        return queryFactory.selectFrom(hashTag)
            .where(
                hashTag.boardId.eq(boardId)
            ).fetch();
    }

}
