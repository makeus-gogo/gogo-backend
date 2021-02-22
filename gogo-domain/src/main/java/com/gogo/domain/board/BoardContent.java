package com.gogo.domain.board;

import com.gogo.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class BoardContent extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String content;

    public BoardContent(Board board, String content) {
        this.board = board;
        this.content = content;
    }

    public static BoardContent newInstance(Board board, String content) {
        return new BoardContent(board, content);
    }

}
