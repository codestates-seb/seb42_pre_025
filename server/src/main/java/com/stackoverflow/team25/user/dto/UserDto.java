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

        public static class ResponseBuilder {
            public ResponseBuilder user(User user) {
                this.userId = user.getUserId();
                this.email = user.getEmail();
                this.displayName = user.getDisplayName();
                this.aboutMe = user.getAboutMe();
                this.acceptRate = user.getAcceptRate();
                this.userStatus = user.getUserStatus();
                return this;
            }
        }
    }
}
