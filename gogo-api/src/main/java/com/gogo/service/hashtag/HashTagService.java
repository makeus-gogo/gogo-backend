package com.gogo.service.hashtag;

import com.gogo.domain.hashtag.HashTag;
import com.gogo.domain.hashtag.HashTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HashTagService {

    private final HashTagRepository hashTagRepository;

    @Transactional
    public List<String> addHashTags(List<String> hashTags, Long boardId, Long memberId) {
        List<HashTag> hashTagList = hashTags.stream()
            .map(hashTag -> HashTag.of(boardId, memberId, hashTag))
            .collect(Collectors.toList());
        hashTagRepository.saveAll(hashTagList);
        return hashTags;
    }

    @Transactional(readOnly = true)
    public List<String> retrieveHashTagsInBoard(Long boardId) {
        return hashTagRepository.getAllHashTagByBoardId(boardId).stream()
            .map(HashTag::getTag)
            .collect(Collectors.toList());
    }

}
