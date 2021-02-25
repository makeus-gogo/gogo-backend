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

    private Long memberId;

    private Long boardId;

    private String description;

    private String status = "ACTIVE";

    public Comment(Long memberId, Long boardId, String description) {
        this.memberId = memberId;
        this.boardId = boardId;
        this.description = description;
    }
}
