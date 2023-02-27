package com.stackoverflow.team25.question.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stackoverflow.team25.answer.dto.AnswerDto;
import com.stackoverflow.team25.question.entity.Question;
import com.stackoverflow.team25.user.dto.UserDto;
import com.stackoverflow.team25.user.entity.User;
import lombok.*;

import javax.persistence.ElementCollection;
import java.util.List;
public class QuestionDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuestionPostDto {
        private Long questionId;
        private String title;
        private String content;
        private Long userId;
        private List<String> tagNames;

    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuestionPatchDto {
        private Long questionId;
        private String title;
        private String content;
        private UserDto.Response userDto;
        private List<String> tagNames;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuestionResponseDto {
        private Long questionId;
        private Integer answerCount;
        private String title;
        private String content;
        private List<AnswerDto.Response> answers;
        @JsonProperty("owner")
        private UserDto.Response userDto;
        private List<String> tagNames;

    }
}