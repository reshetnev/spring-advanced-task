package com.epam.reshetnev.spring.advanced.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.epam.reshetnev.spring.core.domain.User;
import com.google.common.collect.Sets;

public class SecurityUser extends User implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final String ROLE_PREFIX = "ROLE_";

    public SecurityUser(User user) {
        if (user != null) {
            this.setId(user.getId());
            this.setName(user.getName());
            this.setEmail(user.getEmail());
            this.setBirthDay(user.getBirthDay());
            this.setPassword(user.getPassword());
            this.setRoles(user.getRoles());
        }
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {

        String[] roles = this.getRoles().split(",");

        List<String> userRoles = Arrays.asList(roles);

        Set<GrantedAuthority> authorities = Sets.newHashSet();

        if (userRoles != null) {
            userRoles.forEach(role -> authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + role)));
        }

        return authorities;
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
