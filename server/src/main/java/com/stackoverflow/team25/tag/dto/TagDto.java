package com.stackoverflow.team25.tag.dto;

import com.stackoverflow.team25.question.dto.QuestionDto;
import com.stackoverflow.team25.question.entity.Question;
import lombok.*;

import java.util.List;

public class TagDto {
    @Getter
    @Builder
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long tagId;
        private String name;
        private List<QuestionDto.ResponseForTag> questions;
    }
}
