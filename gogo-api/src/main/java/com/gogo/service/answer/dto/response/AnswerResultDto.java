package com.gogo.service.answer.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AnswerResultDto {
    private final Long contentId;
    private final String content;
    private final int check;
    private final double percentage;
}
