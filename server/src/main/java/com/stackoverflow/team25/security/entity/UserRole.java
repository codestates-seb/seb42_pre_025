package com.stackoverflow.team25.security.entity;

import com.stackoverflow.team25.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserRole {
    @Id
    @GeneratedValue
    private Long userRoleId;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
