package com.gogo.service.board.dto.response;

import com.gogo.domain.board.Board;
import com.gogo.domain.board.BoardType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardInfoResponse {

    private final Long id;

    private final String title;

    private final String description;

    private final String pictureUrl;

    private final BoardType type;

    private final Long memberId;

    private final LocalDateTime startDateTime;

    private final LocalDateTime endDateTime;

    public static BoardInfoResponse of(Board board) {
        return new BoardInfoResponse(board.getId(), board.getTitle(), board.getDescription(), board.getPictureUrl(),
            board.getType(), board.getMemberId(), board.getStartDateTime(), board.getEndDateTime());
    }

}
