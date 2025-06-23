package org.rsu.sec.rsusecureapp.audit;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class SecurityAuditInterceptor implements HandlerInterceptor {

    @Autowired
    private AuditService auditService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String username = getCurrentUsername();
        String resource = request.getRequestURI();
        String method = request.getMethod();

        log.info("Request - User: {}, Resource: {}, Method: {}", username, resource, method);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        String username = getCurrentUsername();
        String resource = request.getRequestURI();
        String method = request.getMethod();
        int status = response.getStatus();

        if (ex != null) {
            auditService.logError(username, resource, ex.getMessage());
        } else {
            auditService.logResourceAccess(username, resource, method, String.valueOf(status));
        }
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return "anonymous";
    }
}