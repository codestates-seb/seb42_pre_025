package com.stackoverflow.team25.user.service.impl;

import com.stackoverflow.team25.mail.event.UserRegistrationApplicationEvnet;
import com.stackoverflow.team25.security.entity.Role;
import com.stackoverflow.team25.security.entity.UserRole;
import com.stackoverflow.team25.security.repository.RoleRepository;
import com.stackoverflow.team25.user.entity.User;
import com.stackoverflow.team25.user.repository.UserRepository;
import com.stackoverflow.team25.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ApplicationEventPublisher publisher;

    @Override
    public User createUser(User user) {
        verifyUserByEmail(user);
        setEncodedPassword(user);
        setDefaultUserRole(user);

        User savedUser = userRepository.save(user);
        sendRegistrationEmail(savedUser);

        return savedUser;
    }

    @Override
    public User updateUser(User user) {
        Long userId = user.getUserId();
        User verifiedUser = verifyUserById(userId);
        verifyDeleteUser(verifiedUser);

        Optional.ofNullable(user.getDisplayName())
                .ifPresent(verifiedUser::setDisplayName);
        Optional.ofNullable(user.getAboutMe())
                .ifPresent(verifiedUser::setAboutMe);

        return userRepository.save(verifiedUser);
    }

    @Override
    public User getUser(long userId) {
        //TODO: 삭제된 회원 조회시 더미 데이터 보내기
        return userRepository.findById(userId).orElseThrow(() -> {
            throw new RuntimeException("멤버가 없음");
        });
    }

    @Override
    public Page<User> getUsers(Pageable pageable) {
        PageRequest of = PageRequest.of(pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                pageable.getSort());

        return userRepository.findAll(of);
    }

    @Override
    public void deleteUser(long userId) {
        User user = verifyUserById(userId);
        verifyDeleteUser(user);
        user.setUserStatus(User.UserStatus.USER_DELETE);
        userRepository.save(user);
    }

    private void setDefaultUserRole(User user) {
        Role role = roleRepository.findByRoleName("ROLE_USER");
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        user.setUserRole(userRole);
    }

    private void setEncodedPassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    private void sendRegistrationEmail(User savedUser) {
        publisher.publishEvent(new UserRegistrationApplicationEvnet(this, savedUser));
    }

    private void verifyUserByEmail(User user) {
        String email = user.getEmail();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        optionalUser.ifPresent(s -> {
            throw new RuntimeException("멤버가 이미 존재");
        });
    }

    private User verifyUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> {
                    throw new RuntimeException("멤버가 존재 하지 않음");
                });
    }

    private static void verifyDeleteUser(User user) {
        int userStatus = user.getUserStatus().getNums();
        if (userStatus == 3) {
            throw new RuntimeException("삭제된 회원입니다.");
        }
    }
}
