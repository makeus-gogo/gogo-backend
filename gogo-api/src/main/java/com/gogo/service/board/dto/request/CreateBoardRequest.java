package com.gogo.service.board.dto.request;

import com.gogo.domain.board.Board;
import com.gogo.domain.board.BoardType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class CreateBoardRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private String pictureUrl;

    @NotNull
    private BoardType type;

    private List<BoardContentRequest> contents;

    @Builder
    public CreateBoardRequest(String title, String description, String pictureUrl, BoardType type, List<BoardContentRequest> contents) {
        this.title = title;
        this.description = description;
        this.pictureUrl = pictureUrl;
        this.type = type;
        this.contents = contents;
    }

    public Board toEntity(Long memberId) {
        Board board = Board.builder()
            .memberId(memberId)
            .title(title)
            .description(description)
            .pictureUrl(pictureUrl)
            .type(type)
            .build();

        List<String> boardContentList = contents.stream()
            .map(BoardContentRequest::getContent)
            .collect(Collectors.toList());
        board.addContents(boardContentList);
        return board;
    }

}
