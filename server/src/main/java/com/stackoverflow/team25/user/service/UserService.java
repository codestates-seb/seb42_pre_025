package com.stackoverflow.team25.user.service;

import com.stackoverflow.team25.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User createUser(User user);

    User updateUser(User user);

    User getUser(long userId);

    Page<User> getUsers(Pageable pageable);

    void deleteUser(long userId);
}
