package org.rsu.sec.rsusecureapp.audit;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Instant;

@Component
@Slf4j
public class AuditService {

    private static final Logger securityLogger = LoggerFactory.getLogger("SECURITY");

    public void logAuthentication(String username, String action, String result) {
        securityLogger.info("AUTH - User: {}, Action: {}, Result: {}, IP: {}, Timestamp: {}",
                username, action, result, getCurrentIP(), Instant.now());
    }

    public void logResourceAccess(String username, String resource, String method, String result) {
        securityLogger.info("ACCESS - User: {}, Resource: {}, Method: {}, Result: {}, IP: {}, Timestamp: {}",
                username, resource, method, result, getCurrentIP(), Instant.now());
    }

    public void logError(String username, String action, String error) {
        securityLogger.error("ERROR - User: {}, Action: {}, Error: {}, IP: {}, Timestamp: {}",
                username, action, error, getCurrentIP(), Instant.now());
    }

    private String getCurrentIP() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            return request.getRemoteAddr();
        }
        return "unknown";
    }
}
