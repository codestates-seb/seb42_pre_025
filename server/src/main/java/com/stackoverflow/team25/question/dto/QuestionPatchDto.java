package com.stackoverflow.team25.question.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class QuestionPatchDto {

    @NotBlank
    private long questionId;
    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
/*  @NotBlank
    private OwnerDto owner;
    @NotBlank
    private boolean isAnswered;
    @NotBlank
    private long acceptedAnswerId;
    @NotBlank
    private int answerCount;
    @NotBlank
    private int score;
    @NotBlank
    private String createdAt;
    @NotBlank
    private String modifiedAt;
*/
    @NotBlank(message = "제목은 공백이 아니어야 합니다.")
    @Size(min = 10, max = 100, message = "질문 제목은 10에서 100자 사이여야 합니다.")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
   // private List<AnswerDto> answers;

}
