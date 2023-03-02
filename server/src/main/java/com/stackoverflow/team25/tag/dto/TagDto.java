package com.stackoverflow.team25.tag.dto;

import lombok.Builder;
import lombok.Getter;

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
    }
}
