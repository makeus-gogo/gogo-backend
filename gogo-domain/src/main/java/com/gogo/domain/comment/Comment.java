package com.gogo.domain.comment;

import com.gogo.domain.BaseTimeEntity;
import com.gogo.domain.board.Board;
import com.gogo.domain.member.Member;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String description;

    private String status = "ACTIVE";

    public Comment(Member member, Board board, String description) {
        this.member = member;
        this.board = board;
        this.description = description;
    }
}
