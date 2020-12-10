package com.example.sharper.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Matvey on Nov, 2020
 *
 * Security class - GrantedAuthority
 */

public enum Role implements GrantedAuthority {
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
