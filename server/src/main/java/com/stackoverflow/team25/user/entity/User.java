package com.stackoverflow.team25.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private Long userId;
    private String password;
    private String email;
    private String displayName;
    private String aboutMe = "About ME!";
    private double acceptRate;
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus = UserStatus.USER_ACTIVATE;

    @Getter
    public enum UserStatus {
        USER_ACTIVATE(1, "활동중"),
        USER_SLEEP(2, "휴면"),
        USER_DELETE(3, "회원 삭제");
        private int nums;
        private String desc;

        UserStatus(int nums, String desc) {
            this.nums = nums;
            this.desc = desc;
        }
    }
}
