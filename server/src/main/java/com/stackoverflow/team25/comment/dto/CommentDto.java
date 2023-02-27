package com.stackoverflow.team25.comment.dto;

import com.stackoverflow.team25.user.dto.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class CommentDto {

    @Getter
    @Setter
    @Builder
    public static class POST{
        private Long postId;
        private Long userId;
        private Boolean isEdited;
        private String text;
    }

    @Getter
    @Setter
    @Builder
    public static class PATCH{
        private Long commentId;
        private Boolean isEdited;
        private String text;
    }

    @Getter
    @Setter
    @Builder
    public static class Response{
        private Long commentId;
        private UserDto.Response owner;
        private Long postId;
        private Boolean isEdited;
        private String text;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }
}
