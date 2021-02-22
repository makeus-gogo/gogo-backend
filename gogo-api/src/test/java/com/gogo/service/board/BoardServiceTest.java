package com.gogo.service.board;

import com.gogo.domain.board.*;
import com.gogo.service.board.dto.request.BoardContentRequest;
import com.gogo.service.board.dto.request.CreateBoardRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardContentRepository boardContentRepository;

    @AfterEach
    void cleanUp() {
        boardContentRepository.deleteAllInBatch();
        boardRepository.deleteAllInBatch();
    }

    @Test
    void 새로운_고민_게시물을_등록한다() {
        // given
        String title = "title";
        String description = "description";
        String pictureUrl = "http://pict.com";
        BoardType type = BoardType.MULTI_CHOICE;

        BoardContentRequest content1 = new BoardContentRequest("이게 좋을까요");
        BoardContentRequest content2 = new BoardContentRequest("저게 좋을까요");

        CreateBoardRequest request = CreateBoardRequest.builder()
            .title(title)
            .description(description)
            .pictureUrl(pictureUrl)
            .type(type)
            .contents(Arrays.asList(content1, content2))
            .build();

        // when
        boardService.createBoard(request, 1L);

        // then
        List<Board> boardList = boardRepository.findAll();
        assertThat(boardList).hasSize(1);
        assertThat(boardList.get(0).getTitle()).isEqualTo(title);
        assertThat(boardList.get(0).getDescription()).isEqualTo(description);
        assertThat(boardList.get(0).getType()).isEqualTo(type);
        assertThat(boardList.get(0).getPictureUrl()).isEqualTo(pictureUrl);

        List<BoardContent> boardContentList = boardContentRepository.findAll();
        assertThat(boardContentList).hasSize(2);
        assertThat(boardContentList.get(0).getContent()).isEqualTo(content1.getContent());
        assertThat(boardContentList.get(1).getContent()).isEqualTo(content2.getContent());
    }

}
