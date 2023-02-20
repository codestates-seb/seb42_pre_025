package com.stackoverflow.team25.Question;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class QuestionPostDto {

    @NotBlank
    private long questionid;

 //   private OwnerDto owner;

    private boolean isanswered;

    private long acceptedanswerid;

    private int answercount;

 //   private int score;

    private String createdAt;

    private String modifiedAt;

    @NotBlank(message = "제목은 공백이 아니어야 합니다.")
    @Size(min = 10, max = 100, message = "질문 제목은 10에서 100자 사이여야 합니다.")
    private String title;

    private String text;

//    private List<AnswerDto> answers;

}
