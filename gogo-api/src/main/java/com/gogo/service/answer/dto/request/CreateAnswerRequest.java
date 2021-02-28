package com.gogo.service.answer.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CreateAnswerRequest {
    //답변은 한개만 선택 가능
    @NotBlank
    private Long contentId;
}
