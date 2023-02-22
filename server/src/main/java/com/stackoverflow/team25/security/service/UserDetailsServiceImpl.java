package com.stackoverflow.team25.security.service;

import com.stackoverflow.team25.security.entity.AccountContext;
import com.stackoverflow.team25.user.entity.User;
import com.stackoverflow.team25.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //TODO: LAZY
        Optional<User> optionalUser = userRepository.findByEmail(username);
        User user = optionalUser.orElseThrow(() -> {
            throw new UsernameNotFoundException("UsernameNotFoundException: " + username);
        });

        List<String> userRoles = user.getUserRoles()
                .stream()
                .map(userRole -> userRole.getRole().getRoleName())
                .collect(Collectors.toList());

        List<GrantedAuthority> authorities = userRoles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new AccountContext(user, authorities);
    }
}
