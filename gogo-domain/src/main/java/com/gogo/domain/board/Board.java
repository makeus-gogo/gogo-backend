package com.gogo.domain.board;

import com.gogo.domain.BaseTimeEntity;
import com.gogo.domain.common.DateTimeInterval;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private String description;

    private String pictureUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BoardType type;

    @Embedded
    private DateTimeInterval dateTimeInterval;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<BoardContent> boardContentList = new ArrayList<>();

    @Builder
    public Board(Long memberId, String description, String pictureUrl, BoardType type) {
        final LocalDateTime now = LocalDateTime.now();
        this.memberId = memberId;
        this.description = description;
        this.pictureUrl = pictureUrl;
        this.type = type;
        this.dateTimeInterval = DateTimeInterval.of(now, now.plusDays(7));
    }

    private static Board newInstance(Long memberId, String description, String pictureUrl, BoardType type) {
        return Board.builder()
            .memberId(memberId)
            .description(description)
            .pictureUrl(pictureUrl)
            .type(type)
            .build();
    }

    public static Board newMultiChoiceBoard(Long memberId, String description, String pictureUrl, List<String> contents) {
        Board board = Board.newInstance(memberId, description, pictureUrl, BoardType.MULTI_CHOICE);
        board.addContents(contents);
        return board;
    }

    public static Board newOXInstance(Long memberId, String description, String pictureUrl) {
        Board board = Board.newInstance(memberId, description, pictureUrl, BoardType.OX);
        board.addContents(Arrays.asList("O", "X"));
        return board;
    }

    private void addContents(List<String> contents) {
        for (String content : contents) {
            addContent(content);
        }
    }

    private void addContent(String content) {
        BoardContent boardContent = BoardContent.newInstance(this, content);
        this.boardContentList.add(boardContent);
    }

    public LocalDateTime getStartDateTime() {
        return this.dateTimeInterval.getStartDateTime();
    }

    public LocalDateTime getEndDateTime() {
        return this.dateTimeInterval.getEndDateTime();
    }

}
