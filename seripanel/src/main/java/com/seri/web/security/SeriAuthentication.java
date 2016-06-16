package com.seri.web.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Map;

/**
 * Created by puneet on 07/04/16.
 */
public class SeriAuthentication implements Authentication {
    protected String uid;
    protected Collection<GrantedAuthority> authorities;
    //protected User details;
    protected Map<String, String> details;


    public SeriAuthentication(String uid, Collection<GrantedAuthority> authorities, Map<String, String> details) {
        this.uid = uid;
        this.authorities = authorities;
        this.details = details;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return details;
    }

    @Override
    public Object getPrincipal() {
        return uid;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    }

    @Override
    public String getName() {
        return uid;
    }
}
