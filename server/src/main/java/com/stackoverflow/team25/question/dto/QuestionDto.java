package com.stackoverflow.team25.question.dto;

import com.stackoverflow.team25.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class QuestionDto {

        @Getter
        public static class QuestionPostDto {
            private Long questionId;
            private String title;
            private String content;
        }

        @Getter
        @Setter
        public static class QuestionPatchDto {
            private Long questionId;
            private String title;
            private String content;
        }

        @Getter
        @Builder
        public static class QuestionResponseDto {
            private Long questionId;
            private String title;
            private String content;

        }

}