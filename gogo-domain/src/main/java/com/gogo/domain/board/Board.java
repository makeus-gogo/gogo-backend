package com.gogo.domain.board;

import com.gogo.domain.BaseTimeEntity;
import com.gogo.domain.common.DateTimeInterval;
import com.gogo.domain.common.Uuid;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Uuid uuid;

    private Long memberId;

    private String title;

    private String description;

    private String pictureUrl;

    private BoardType type;

    @Embedded
    private DateTimeInterval dateTimeInterval;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<BoardContent> boardContentList = new ArrayList<>();

    @Builder
    public Board(Long memberId, String title, String description, String pictureUrl, BoardType type) {
        final LocalDateTime now = LocalDateTime.now();
        this.uuid = Uuid.newInstance();
        this.memberId = memberId;
        this.title = title;
        this.description = description;
        this.pictureUrl = pictureUrl;
        this.type = type;
        this.dateTimeInterval = DateTimeInterval.of(now, now.plusDays(7));
    }

    private static Board newInstance(Long memberId, String title, String description, String pictureUrl, BoardType type) {
        return Board.builder()
            .memberId(memberId)
            .title(title)
            .description(description)
            .pictureUrl(pictureUrl)
            .type(type)
            .build();
    }

    public static Board newMultiChoiceBoard(Long memberId, String title, String description, String pictureUrl, List<String> contents) {
        Board board = Board.newInstance(memberId, title, description, pictureUrl, BoardType.MULTI_CHOICE);
        board.addContents(contents);
        return board;
    }

    public static Board newOXInstance(Long memberId, String title, String description, String pictureUrl) {
        return Board.newInstance(memberId, title, description, pictureUrl, BoardType.OX);
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

    public String getUuid() {
        return this.uuid.getUuid();
    }

    public LocalDateTime getStartDateTime() {
        return this.dateTimeInterval.getStartDateTime();
    }

    public LocalDateTime getEndDateTime() {
        return this.dateTimeInterval.getEndDateTime();
    }

}
