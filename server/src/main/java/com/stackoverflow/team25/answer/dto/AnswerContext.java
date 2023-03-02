package com.stackoverflow.team25.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnswerContext {
    private Long userId;
    private Long answerId;
    private String content;
}
