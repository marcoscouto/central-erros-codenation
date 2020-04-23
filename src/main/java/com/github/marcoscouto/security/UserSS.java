package com.github.marcoscouto.security;

import com.github.marcoscouto.domain.User;
import com.github.marcoscouto.domain.enums.ProfileEnum;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class UserSS implements UserDetails {

    @Getter
    private Long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSS() {
    }

    public UserSS(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.authorities = Arrays.asList(new SimpleGrantedAuthority(user.getProfile().toString()));
    }


    public UserSS(Long id, String email, String password, ProfileEnum profileEnum) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = Arrays.asList(new SimpleGrantedAuthority(profileEnum.toString()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
