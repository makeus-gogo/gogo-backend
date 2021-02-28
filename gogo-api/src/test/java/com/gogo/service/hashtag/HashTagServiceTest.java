package com.gogo.service.hashtag;

import com.gogo.domain.hashtag.HashTag;
import com.gogo.domain.hashtag.HashTagCreator;
import com.gogo.domain.hashtag.HashTagRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HashTagServiceTest {

    @Autowired
    private HashTagService hashTagService;

    @Autowired
    private HashTagRepository hashTagRepository;

    @AfterEach
    void cleanUp() {
        hashTagRepository.deleteAll();
    }

    @Test
    void 게시물에_해시태그를_추가한다() {
        // given
        List<String> hashTags = Arrays.asList("#음식", "#먹방");
        Long boardId = 100L;

        // when
        hashTagService.addHashTags(hashTags, boardId, 1L);

        // then
        List<HashTag> hashTagList = hashTagRepository.findAll();
        assertThat(hashTagList).hasSize(2);
        assertThat(hashTagList.get(0).getTag()).isEqualTo(hashTags.get(0));
        assertThat(hashTagList.get(1).getTag()).isEqualTo(hashTags.get(1));
    }

    @Test
    void 게시물에_해당하는_해시태그들을_조회한다() {
        // given
        Long boardId = 100L;
        hashTagRepository.saveAll(Arrays.asList(
            HashTagCreator.create("#음악", boardId),
            HashTagCreator.create("#발라드", boardId),
            HashTagCreator.create("#멜로디", boardId)
        ));

        // when
        List<String> tags = hashTagService.retrieveHashTagsInBoard(boardId);

        // then
        assertThat(tags).hasSize(3);
        assertThat(tags.get(0)).isEqualTo("#음악");
        assertThat(tags.get(1)).isEqualTo("#발라드");
        assertThat(tags.get(2)).isEqualTo("#멜로디");
    }

}
