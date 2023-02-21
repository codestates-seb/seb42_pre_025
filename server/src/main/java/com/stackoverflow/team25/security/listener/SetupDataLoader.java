package com.stackoverflow.team25.security.listener;

import com.stackoverflow.team25.security.entity.Role;
import com.stackoverflow.team25.security.entity.UserRole;
import com.stackoverflow.team25.security.repository.RoleRepository;
import com.stackoverflow.team25.user.entity.User;
import com.stackoverflow.team25.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        setupSecurityResources();
        alreadySetup = true;
    }

    private void setupSecurityResources() {
        Role adminRole = createRoleIfNotFound("ROLE_ADMIN", "관리자 권한");
        Role managerRole = createRoleIfNotFound("ROLE_MANAGER", "매니저 권한");
        Role userRole = createRoleIfNotFound("ROLE_USER", "유저 권한");

        UserRole adminUserRole = createUserRole(adminRole);
        UserRole managerUserRole = createUserRole(managerRole);
        UserRole userUserRole = createUserRole(userRole);

        createUserIfNotFound("admin@test.com", "1111", adminUserRole);
        createUserIfNotFound("manager@test.com", "1111", managerUserRole);
        createUserIfNotFound("user@test.com", "1111", userUserRole);
    }

    @Transactional
    public Role createRoleIfNotFound(String roleName, String roleDesc) {
        Role role = roleRepository.findByRoleName(roleName);

        if (role == null) {
            role = Role.builder()
                    .roleName(roleName)
                    .roleDesc(roleDesc)
                    .build();
        }
        return roleRepository.save(role);
    }

    @Transactional
    public void createUserIfNotFound(String email, String password, UserRole userRole) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        String encodedPassword = passwordEncoder.encode(password);

        User user = optionalUser.orElse(
                User.builder()
                        .email(email)
                        .password(encodedPassword)
                        .userRoles(List.of(userRole))
                        .build()
        );

        userRole.setUser(user);

        userRepository.save(user);
    }

    private UserRole createUserRole(Role role) {
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        return userRole;
    }
}
