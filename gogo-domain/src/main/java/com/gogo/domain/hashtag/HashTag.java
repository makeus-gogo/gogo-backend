package com.gogo.domain.hashtag;

import com.gogo.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class HashTag extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long boardId;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private String tag;

    private HashTag(Long boardId, Long memberId, String tag) {
        this.boardId = boardId;
        this.memberId = memberId;
        this.tag = tag;
    }

    public static HashTag of(Long boardId, Long memberId, String tag) {
        return new HashTag(boardId, memberId, tag);
    }

}
