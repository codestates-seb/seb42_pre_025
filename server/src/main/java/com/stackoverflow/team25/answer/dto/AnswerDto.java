package com.stackoverflow.team25.answer.dto;
import com.stackoverflow.team25.question.entity.Question;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AnswerDto {
    @Setter
    @Getter
    public static class Post{
//        @NotNull(message = "userId가 없습니다.")
//        private Long userId;
        private Long questionId;
        private Long score;
        private Boolean isAccepted;
        private String content;
    }
    @Getter
    public static class Patch{
        @NotBlank
        private String content;
    }

    @Setter
    @Getter
    public static class Response{
        private Long answerId;
        //Todo: 사용자 객체
//    private Owner owner;
        private Long questionId;
        private Long score;
        private Boolean isAccepted;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }
}
