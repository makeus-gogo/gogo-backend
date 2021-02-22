package com.gogo.service.board.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardContentRequest {

    private String content;

    public BoardContentRequest(String content) {
        this.content = content;
    }

}
