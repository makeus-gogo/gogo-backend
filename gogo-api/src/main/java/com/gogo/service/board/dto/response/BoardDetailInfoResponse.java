package com.gogo.service.board.dto.response;

import com.gogo.domain.board.Board;
import com.gogo.domain.board.BoardType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardDetailInfoResponse {

    private final Long id;

    private final String title;

    private final String description;

    private final BoardType type;

    private final Long memberId;

    private final LocalDateTime startDateTime;

    private final LocalDateTime endDateTime;

    private final List<BoardContentResponse> contents = new ArrayList<>();

    private final List<String> hashTags = new ArrayList<>();

    public static BoardDetailInfoResponse of(Board board, List<String> hashTags) {
        BoardDetailInfoResponse response = new BoardDetailInfoResponse(board.getId(), board.getTitle(),
            board.getDescription(), board.getType(), board.getMemberId(), board.getStartDateTime(), board.getEndDateTime());
        List<BoardContentResponse> contentResponses = board.getBoardContentList().stream()
            .map(BoardContentResponse::of)
            .collect(Collectors.toList());
        response.contents.addAll(contentResponses);
        response.hashTags.addAll(hashTags);
        return response;
    }

}
