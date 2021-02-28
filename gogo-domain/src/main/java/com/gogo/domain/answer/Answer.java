package com.gogo.domain.answer;

import com.gogo.domain.BaseTimeEntity;
import com.gogo.domain.board.Board;
import com.gogo.domain.board.BoardContent;
import com.gogo.domain.member.Member;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Answer extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_content_id")
    private BoardContent boardContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String status = "ACTIVE";

    public Answer(Member member, Board board,BoardContent boardContent) {
        this.member = member;
        this.board = board;
        this.boardContent = boardContent;
    }
}
