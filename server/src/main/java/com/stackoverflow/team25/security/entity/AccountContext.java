package com.stackoverflow.team25.security.entity;

import com.stackoverflow.team25.user.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
public class AccountContext extends org.springframework.security.core.userdetails.User {
    private User user;

    public AccountContext(User user, List<GrantedAuthority> roles) {
        super(user.getEmail(), user.getPassword(), roles);
        this.user = user;
    }
}
