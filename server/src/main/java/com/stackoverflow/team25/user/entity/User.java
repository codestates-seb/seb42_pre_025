package com.stackoverflow.team25.user.entity;

import com.stackoverflow.team25.answer.entity.Answer;
import com.stackoverflow.team25.security.entity.UserRole;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.stackoverflow.team25.user.entity.User.UserStatus.USER_ACTIVATE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.*;
import static lombok.Builder.Default;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long userId;
    private String password;
    private String email;
    private String displayName;
    @Default
    private String aboutMe = "About ME!";
    private double acceptRate;
    @Default
    @OneToMany(mappedBy = "user", cascade = PERSIST)
    @Setter(PRIVATE)
    private List<UserRole> userRoles = new ArrayList<>();
    @Default
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus = USER_ACTIVATE;
    @Default
    @OneToMany(mappedBy = "owner", cascade = REMOVE, fetch = LAZY)
    private List<Answer> answers = new ArrayList<>();

    public void setUserRole(UserRole userRole) {
        this.userRoles.add(userRole);
        if (userRole.getUser() != this) {
            userRole.setUser(this);
        }
    }
    // Answer 편의 메서드
    public void addAnswer(Answer answer){
        answers.add(answer);
        if(answer.getOwner() != this){
            answer.addUser(this);
        }
    }

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
