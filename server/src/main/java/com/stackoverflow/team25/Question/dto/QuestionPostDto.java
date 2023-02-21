package com.stackoverflow.team25.Question.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class QuestionPostDto {
    @NotNull
    private Long questionId;

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @NotBlank
    private String title;


    public void setTitle(String title) {
        this.title = title;
    }

    @NotBlank
    private String text;

    public void setContent(String text) {
        this.text = text;
    }
}
