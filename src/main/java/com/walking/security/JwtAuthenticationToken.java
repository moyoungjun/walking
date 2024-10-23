package com.walking.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;
    private String credential;


    public JwtAuthenticationToken(Object principal, String credential) {
        super(null);
        this.credential = credential;
        this.principal = principal;
        setAuthenticated(false);
    }

    public JwtAuthenticationToken(Object principal, String credential, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.credential = credential;
        this.principal = principal;
        setAuthenticated(true);
    }


    @Override
    public String getCredentials() {
        return credential;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
