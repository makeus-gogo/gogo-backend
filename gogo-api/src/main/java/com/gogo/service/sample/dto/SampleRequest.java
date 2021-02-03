package com.gogo.service.sample.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class SampleRequest {

    @NotBlank
    private String sample;

}
