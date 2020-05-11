package org.makar.sweater.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;

import java.util.Collection;

public enum Role implements GrantedAuthority {
    USER,ADMIN;
    @Override
    public String getAuthority() {
        return name();
    }
}
