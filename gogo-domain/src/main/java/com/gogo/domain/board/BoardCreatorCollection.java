package com.gogo.domain.board;

import com.gogo.domain.member.Member;
import com.gogo.domain.member.MemberRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BoardCreatorCollection {

    private final Map<Long, Member> memberMap = new HashMap<>();

    private BoardCreatorCollection(MemberRepository memberRepository, List<Board> boardList) {
        this.memberMap.putAll(findMembersInBoardList(memberRepository, boardList));
    }

    private Map<Long, Member> findMembersInBoardList(MemberRepository memberRepository, List<Board> boardList) {
        return memberRepository.findAllById(getMemberIdsFrom(boardList)).stream()
            .collect(Collectors.toMap(Member::getId, member -> member));
    }

    private Set<Long> getMemberIdsFrom(List<Board> boardList) {
        return boardList.stream()
            .map(Board::getMemberId)
            .collect(Collectors.toSet());
    }

    public static BoardCreatorCollection of(MemberRepository memberRepository, List<Board> boardList) {
        return new BoardCreatorCollection(memberRepository, boardList);
    }

    public Member getCreator(Long memberId) {
        return memberMap.get(memberId);
    }

}
