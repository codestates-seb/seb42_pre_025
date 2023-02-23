package com.stackoverflow.team25.answer.dto;
import com.stackoverflow.team25.question.entity.Question;
import com.stackoverflow.team25.user.dto.UserDto;
import com.stackoverflow.team25.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AnswerDto {
    @Setter
    @Getter
    public static class Post{
        private Long userId;
        private Long questionId;
        private Long score;
        private Boolean isAccepted;
        private String content;
    }
    @Setter
    @Getter
    public static class Patch{
        private Long answerId;
        @NotBlank
        private String content;
    }


    @Getter
    @Builder
    public static class Response{
        private Long answerId;
        private UserDto.Response owner;
        private Long questionId;
        private Long score;
        private Boolean isAccepted;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }
}
