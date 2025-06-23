package org.rsu.sec.rsusecureapp.audit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class AuthenticationEventListener {

    @Autowired
    private AuditService auditService;

    @EventListener
    public void handleAuthenticationSuccess(AuthenticationSuccessEvent event) {
        String username = event.getAuthentication().getName();
        auditService.logAuthentication(username, "LOGIN", "SUCCESS");
    }

    @EventListener
    public void handleAuthenticationFailure(AbstractAuthenticationFailureEvent event) {
        String username = event.getAuthentication().getName();
        auditService.logAuthentication(username, "LOGIN", "FAILURE: " + event.getException().getMessage());
    }

    @EventListener
    public void handleLogout(SessionDestroyedEvent event) {
        List<SecurityContext> contexts = event.getSecurityContexts();
        for (SecurityContext context : contexts) {
            Authentication auth = context.getAuthentication();
            if (auth != null) {
                auditService.logAuthentication(auth.getName(), "LOGOUT", "SUCCESS");
            }
        }
    }
}
