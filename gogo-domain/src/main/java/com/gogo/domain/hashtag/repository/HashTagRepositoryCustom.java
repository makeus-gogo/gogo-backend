package com.gogo.domain.hashtag.repository;

import com.gogo.domain.hashtag.HashTag;

import java.util.List;

public interface HashTagRepositoryCustom {

    List<HashTag> getAllHashTagByBoardId(Long boardId);

}
