package com.stackoverflow.team25.question.dto;

import lombok.Getter;

@Getter
public class QuestionResponseDto {
    private Long questionId;
    private String title;
    private String text;
 //   private List<AnswerResponseDto> answers;
}