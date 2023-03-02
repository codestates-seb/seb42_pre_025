package com.stackoverflow.team25.security.config;

import com.stackoverflow.team25.security.filter.JwtAuthenticationFilter;
import com.stackoverflow.team25.security.filter.JwtVerificationFilter;
import com.stackoverflow.team25.security.handler.JwtAuthenticationSuccessHandler;
import com.stackoverflow.team25.security.handler.OAuth2SuccessHandler;
import com.stackoverflow.team25.security.jwt.JwtTokenizer;
import com.stackoverflow.team25.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenizer jwtTokenizer;
    private final UserService userService;

    @Bean
    public SecurityFilterChain filetChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .antMatchers(HttpMethod.POST, "/api/questions/**").hasRole("USER")
                        .antMatchers(HttpMethod.PATCH, "api/questions/**").hasRole("USER")
                        .antMatchers(HttpMethod.DELETE, "api/questions/**").hasRole("USER")
                        .antMatchers(HttpMethod.POST, "api/answers/**").hasRole("USER")
                        .antMatchers(HttpMethod.PATCH, "api/answers/**").hasRole("USER")
                        .antMatchers(HttpMethod.DELETE, "api/answers/**").hasRole("USER")
                        .antMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PATCH, "/api/users/**").hasRole("USER")
                        .antMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("USER")
                        .anyRequest().permitAll()
                );
        http
                .formLogin()
                .permitAll();
        http
                .cors(httpSecurityCorsConfigurer -> corsConfigurationSource());
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .csrf().disable();
        http
                .headers().frameOptions().disable();
        http
                .apply(new CustomFilterConfigurer());
        http
                .oauth2Login().successHandler(new OAuth2SuccessHandler(userService));
        //TODO:: 로그아웃 핸들러 >> redirect "/" >> localhost:3000
        http
                .logout()
                .logoutUrl("/api/logout")
                .logoutSuccessUrl("/");

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("Authorization", "Location", "Refresh"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Configuration
    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);
            jwtAuthenticationFilter.setFilterProcessesUrl("/api/login");
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new JwtAuthenticationSuccessHandler());

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer);
            http
                    .addFilterAt(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    }
}

