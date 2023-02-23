package com.stackoverflow.team25.user.entity;

import com.stackoverflow.team25.answer.entity.Answer;
import com.stackoverflow.team25.security.entity.UserRole;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String password;
    private String email;
    private String displayName;
    @Builder.Default
    private String aboutMe = "About ME!";
    private double acceptRate;
    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<UserRole> userRoles = new ArrayList<>();
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus = UserStatus.USER_ACTIVATE;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
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
