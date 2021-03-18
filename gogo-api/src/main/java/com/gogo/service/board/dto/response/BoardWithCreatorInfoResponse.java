package com.gogo.service.board.dto.response;

import com.gogo.domain.board.Board;
import com.gogo.domain.member.Member;
import com.gogo.service.member.dto.response.MemberInfoResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardWithCreatorInfoResponse {

    private final BoardInfoResponse board;

    private final MemberInfoResponse creator;

    public static BoardWithCreatorInfoResponse of(Board board, Member member) {
        return new BoardWithCreatorInfoResponse(BoardInfoResponse.of(board), MemberInfoResponse.of(member));
    }

}
