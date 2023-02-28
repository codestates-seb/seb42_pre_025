package com.stackoverflow.team25.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PrincipalContext {
    private String username;
    private Long userId;

    public static Long getUserId() {
        PrincipalContext principalContext = (PrincipalContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principalContext.userId;
    }

    public static String getUserName() {
        PrincipalContext principalContext = (PrincipalContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principalContext.username;
    }
}
