package com.gogo.domain.hashtag;

import com.gogo.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class HashTag extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long boardId;

    private Long memberId;

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
