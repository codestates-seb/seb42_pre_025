package com.stackoverflow.team25.security.handler;

import com.stackoverflow.team25.security.entity.Role;
import com.stackoverflow.team25.security.entity.UserRole;
import com.stackoverflow.team25.security.jwt.JwtTokenizer;
import com.stackoverflow.team25.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenizer jwtTokenizer;

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        super.onAuthenticationSuccess(request, response, authentication);
        log.info("# Success OAuth 2.0 Login");
        OAuth2User principal = (OAuth2User) authentication.getPrincipal();

        String email = (String) principal.getAttributes().get("email");
        String name = (String) principal.getAttributes().get("name");
        log.info("email: {}", email);
        log.info("name: {}", name);

        User user = User.builder()
                .email(email)
                .displayName(name)
                .password(UUID.randomUUID().toString())
                .build();

        String accessToken = delegateAccessToken(user);
        String refreshToken = delegateRefreshToken(user);

        String bearerToken = "Bearer " + accessToken;
        response.setHeader("Authorization", bearerToken);
        response.setHeader("Refresh", refreshToken);

        String authorization = response.getHeader("Authorization");
        String refresh = response.getHeader("Refresh");
        log.info("AccessToken: {}", accessToken);
        log.info("authorization: {}", authorization);
        log.info("RefreshToken: {}", refreshToken);
        log.info("refresh: {}", refresh);



        String uri = createURI(bearerToken, refreshToken).toString();
        getRedirectStrategy().sendRedirect(request, response, uri);
    }

    private String delegateAccessToken(User user) {
        Map<String, Object> claims = new HashMap<>();

        List<String> roles = user.getUserRoles().stream()
                .map(UserRole::getRole)
                .map(Role::getRoleName)
                .collect(Collectors.toList());
        claims.put("username", user.getEmail());
        claims.put("roles", roles);

        String subject = user.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    private String delegateRefreshToken(User user) {
        String subject = user.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }

    private URI createURI(String accessToken, String refreshToken) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("access_token", accessToken);
        queryParams.add("refresh_token", refreshToken);

        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("localhost")
                .port(3000)
                .path("/")
                .queryParams(queryParams)
                .build()
                .toUri();
    }

}
