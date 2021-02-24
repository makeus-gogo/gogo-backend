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
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private String uuid;

    private String description;

    private String status = "ACTIVE";

    public Comment(Long memberId, String uuid, String description) {
        this.memberId = memberId;
        this.uuid = uuid;
        this.description = description;
    }
}
