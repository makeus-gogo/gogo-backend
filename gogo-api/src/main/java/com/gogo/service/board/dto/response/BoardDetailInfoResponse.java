package com.gogo.service.board.dto.response;

import com.gogo.domain.board.Board;
import com.gogo.domain.board.BoardType;
import com.gogo.domain.member.Member;
import com.gogo.service.member.dto.response.MemberInfoResponse;
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

    private final String description;

    private final String pictureUrl;

    private final BoardType type;

    private final Long memberId;

    private final LocalDateTime startDateTime;

    private final LocalDateTime endDateTime;

    private final MemberInfoResponse creator;

    private final List<BoardContentResponse> contents = new ArrayList<>();

    private final List<String> hashTags = new ArrayList<>();


    public static BoardDetailInfoResponse of(Board board, List<String> hashTags, Member creator) {
        BoardDetailInfoResponse response = new BoardDetailInfoResponse(board.getId(), board.getDescription(),
            board.getPictureUrl(), board.getType(), board.getMemberId(), board.getStartDateTime(), board.getEndDateTime(), MemberInfoResponse.of(creator));
        List<BoardContentResponse> contentResponses = board.getBoardContentList().stream()
            .map(BoardContentResponse::of)
            .collect(Collectors.toList());
        response.contents.addAll(contentResponses);
        response.hashTags.addAll(hashTags);
        return response;
    }

}
