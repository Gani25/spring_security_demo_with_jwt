package com.sprk.spring_security_demo.config;

import com.sprk.spring_security_demo.entity.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

//    FIELDS
    private List<GrantedAuthority> authorities;

    private String username;

    private String password;

//    CONSTRUCTOR
    public CustomUserDetails(UserInfo userInfo) {
        this.username = userInfo.getUsername();
        this.password = userInfo.getPassword();
        this.authorities = Arrays.stream(userInfo.getRoles().split(","))
                .map(String::trim)
                .<GrantedAuthority>map(SimpleGrantedAuthority::new)
                .toList();

        /*String roles = userInfo.getRoles();

        String[] rolesArr = roles.split(",");

        List<GrantedAuthority> authorities = new ArrayList<>();
        for(String role:rolesArr){

            authorities.add(new SimpleGrantedAuthority(role.trim()));
        }

        this.authorities = authorities;*/

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
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
