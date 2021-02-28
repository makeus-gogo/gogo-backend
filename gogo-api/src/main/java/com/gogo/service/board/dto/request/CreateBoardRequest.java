package com.gogo.service.board.dto.request;

import com.gogo.domain.board.Board;
import com.gogo.domain.board.BoardType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreateBoardRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private String pictureUrl;

    private List<String> contents;

    private BoardType type;

    @NotNull
    private List<String> hashTags;

    @Builder(builderMethodName = "testBuilder")
    public CreateBoardRequest(@NotBlank String title, @NotBlank String description, String pictureUrl, List<String> contents, BoardType type, List<String> hashTags) {
        this.title = title;
        this.description = description;
        this.pictureUrl = pictureUrl;
        this.contents = contents;
        this.type = type;
        this.hashTags = hashTags;
    }

    // TODO 좀 더 OOP 적으로 생각
    public Board toEntity(Long memberId) {
        if (type.equals(BoardType.MULTI_CHOICE)) {
            return Board.newMultiChoiceBoard(memberId, title, description, pictureUrl, contents);
        }
        return Board.newOXInstance(memberId, title, description, pictureUrl);
    }

}
