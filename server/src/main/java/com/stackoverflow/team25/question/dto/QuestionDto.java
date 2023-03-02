package com.stackoverflow.team25.question.dto;

import com.stackoverflow.team25.answer.dto.AnswerDto;
import com.stackoverflow.team25.question.entity.Question;
import com.stackoverflow.team25.question.entity.Question.QuestionType;
import com.stackoverflow.team25.user.dto.UserDto;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionDto {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        private Long questionId;
        private String title;
        private String content;
        private Long userId;
        private List<String> tags;

    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch {
        private Long questionId;
        private String title;
        private String content;
        private Long userId;
        private List<String> tags;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long questionId;
        private Integer answerCount;
        private String title;
        private String content;
        private List<AnswerDto.Response> answers;
        private UserDto.Response owner;
        private List<String> tags;
        private QuestionType questionType;

        public Response(Question question) {
            this.questionId = question.getQuestionId();
            this.answerCount = question.getAnswerCount();
            this.title = question.getTitle();
            this.content = question.getContent();
            this.answers = question.getAnswers().stream().map(answer -> AnswerDto.Response.builder()
                    .answerId(answer.getAnswerId())
                    .owner(new UserDto.Response(answer.getOwner()))
                    .questionId(answer.getQuestion().getQuestionId())
                    .score(answer.getScore())
                    .isAccepted(answer.getIsAccepted())
                    .content(answer.getContent())
                    .createdAt(answer.getCreatedAt())
                    .modifiedAt(answer.getModifiedAt())
                    .build()).collect(Collectors.toList());

            this.owner = UserDto.Response.builder()
                    .userId(question.getUser().getUserId())
                    .email(question.getUser().getEmail())
                    .displayName(question.getUser().getDisplayName())
                    .aboutMe(question.getUser().getAboutMe())
                    .userStatus(question.getUser().getUserStatus())
                    .build();

            this.questionType = question.getQuestionType();
            this.tags = question.getQuestionTags().stream().map(questionTag -> questionTag.getTag().getName()).collect(Collectors.toList());
        }
    }
}