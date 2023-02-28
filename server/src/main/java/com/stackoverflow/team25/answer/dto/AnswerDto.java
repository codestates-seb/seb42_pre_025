package com.stackoverflow.team25.answer.dto;

import com.stackoverflow.team25.answer.entity.Answer;
import com.stackoverflow.team25.user.dto.UserDto;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class AnswerDto {
    @Setter
    @Getter
    @AllArgsConstructor
    public static class Post {
        private Long userId;
        private Long questionId;
        private Long score;
        private Boolean isAccepted;
        private String content;
    }

    @Setter
    @Getter
    public static class Patch {
        private Long answerId;
        private Long userId;
        @NotBlank
        private String content;
    }


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long answerId;
        private UserDto.Response owner;
        private Long questionId;
        private Long score;
        private Boolean isAccepted;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public Response(Answer answer) {
            this.answerId = answer.getAnswerId();
            this.owner = new UserDto.Response(answer.getOwner());
            this.questionId = answer.getQuestion().getQuestionId();
            this.score = answer.getScore();
            this.isAccepted = answer.getIsAccepted();
            this.content = answer.getContent();
            this.createdAt = answer.getCreatedAt();
            this.modifiedAt = answer.getModifiedAt();
        }
    }
}
