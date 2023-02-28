package com.stackoverflow.team25.tag.dto;

import com.stackoverflow.team25.question.entity.Question;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class TagDto {
    @Getter
    @Builder
    public static class Post {
        private String tagName;
    }
    @Getter
    @Builder
    public static class Response {
        private Long tagId;
        private String name;
        private List<Question> questions;
    }
}
