package com.stackoverflow.team25.security.handler;

import com.stackoverflow.team25.user.entity.User;
import com.stackoverflow.team25.user.service.UserService;
import com.stackoverflow.team25.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserService userService;

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("# Success OAuth 2.0 Login");
        User principal = userService.getUser(authentication);
        String email = principal.getEmail();
        String name = principal.getDisplayName();
        log.info("# principal: {}, {}", email, name);

        User user = User.builder()
                .email(email)
                .displayName(name)
                .password(UUID.randomUUID().toString())
                .build();
        Optional<User> optionalUser = userService.findUserByEmail(email);

        if (optionalUser.isEmpty()) {
            user = userService.createUser(user);
        } else {
            user = optionalUser.get();
        }

        String accessToken = userService.delegateAccessToken(user);
        String refreshToken = userService.delegateRefreshToken(user);
        String bearerToken = "Bearer " + accessToken;

        String uri = UriCreator.createURI(bearerToken, refreshToken).toString();

        getRedirectStrategy().sendRedirect(request, response, uri);
    }
}
