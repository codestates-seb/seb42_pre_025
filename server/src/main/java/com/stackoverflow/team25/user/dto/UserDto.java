package com.stackoverflow.team25.user.dto;

import com.stackoverflow.team25.user.entity.User;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserDto {
    @Getter
    public static class Post {
        @Email
        private String email;
        @NotNull
        private String password;
        @NotNull
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
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long userId;
        private String email;
        private String displayName;
        private String aboutMe;
        private double acceptRate;
        private User.UserStatus userStatus;

        public Response(User user) {
            this.userId = user.getUserId();
            this.email = user.getEmail();
            this.displayName = user.getDisplayName();
            this.aboutMe = user.getAboutMe();
            this.acceptRate = user.getAcceptRate();
            this.userStatus = user.getUserStatus();
        }
    }

}
