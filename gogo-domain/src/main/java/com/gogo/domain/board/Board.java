package com.gogo.domain.board;

import com.gogo.domain.BaseTimeEntity;
import com.gogo.domain.common.Uuid;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardContent> boardContentList = new ArrayList<>();

    @Builder
    public Board(Long memberId, String title, String description, String pictureUrl, BoardType type) {
        this.uuid = Uuid.newInstance();
        this.memberId = memberId;
        this.title = title;
        this.description = description;
        this.pictureUrl = pictureUrl;
        this.type = type;
    }

    public void addContents(List<String> contents) {
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

}
