package com.example.portalsecurity.config;

import com.example.portalsecurity.model.Document;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class DocumentPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (targetDomainObject instanceof Document document) {
            String username = ((UserDetails) authentication.getPrincipal()).getUsername();
            if ("read".equals(permission) || "write".equals(permission))
                return document.getOwner().equals(username);
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
