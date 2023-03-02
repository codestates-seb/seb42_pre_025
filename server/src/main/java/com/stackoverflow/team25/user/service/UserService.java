package com.stackoverflow.team25.user.service;

import com.stackoverflow.team25.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface UserService {

    User createUser(User user);

    User updateUser(User user);

    User getUser(long userId);

    Page<User> getUsers(Pageable pageable);

    void deleteUser(long userId);

    Optional<User> findUserByEmail(String email);

    User getUser(Authentication authentication);

    String delegateAccessToken(User user);

    String delegateRefreshToken(User user);
}
