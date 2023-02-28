package com.stackoverflow.team25.user.dto;

import com.stackoverflow.team25.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class UserDto {
    @Getter
    public static class Post {
        private String email;
        private String password;
        private String displayName;
    }

    @Getter
    @Setter
    public static class Patch {
        private Long userId;
        private String displayName;
        private String aboutMe;
    }

    @Getter
    @Setter
    @Builder
    public static class Response {
        private Long userId;
        private String email;
        private String displayName;
        private String aboutMe;
        private double acceptRate;
        private User.UserStatus userStatus;
    }
}
