package com.stackoverflow.team25.tag.dto;

import com.stackoverflow.team25.question.entity.Question;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class TagDto {
    @Getter
    @Builder
    public static class Response {
        private Long TagId;
        private String Name;
        private List<Question> questions;
    }
}
