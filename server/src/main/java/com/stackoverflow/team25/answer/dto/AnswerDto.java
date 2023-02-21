package com.stackoverflow.team25.answer.dto;
import lombok.Getter;
import lombok.Setter;

public class AnswerDto {
    @Getter
    public static class Post{
        private Long score;
        private Boolean isAccepted;
        private String content;
    }
    @Getter
    public static class Patch{
        private String content;
    }

    @Setter
    public static class Response{
        private Long answerId;

        //Todo: 사용자 객체
//    private Owner owner;

        //Todo: 외래키
//    private Long questionId;
        private Long score;
        private Boolean isAccepted;
        private String content;
    }
}
