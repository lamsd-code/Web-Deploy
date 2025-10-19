package com.example.demo.security;

import com.example.demo.constant.SystemConstant;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    public String determineTargetUrl(Authentication authentication) {
        if (authentication == null) {
            return SystemConstant.HOME;
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = authorities == null
                ? List.of()
                : authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        // Nếu staff hoặc admin/manager -> vào admin, còn lại về home
        if (isStaff(roles) || isAdmin(roles)) {
            return SystemConstant.ADMIN_HOME;
        }
        return SystemConstant.HOME;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    private boolean isAdmin(List<String> roles) {
        return roles != null && (roles.contains(SystemConstant.ADMIN_ROLE) || roles.contains(SystemConstant.MANAGER_ROLE));
    }

    private boolean isStaff(List<String> roles) {
        return roles != null && roles.contains(SystemConstant.STAFF_ROLE);
    }
}