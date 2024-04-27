package com.leon.springboot.security.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.leon.springboot.db1.model.UserAuth;
import com.leon.springboot.security.config.UserAuthority;

public class AppUserDetails implements UserDetails {
	private final UserAuth user;

    public AppUserDetails(UserAuth user) {
        this.user = user;
    }

    // 必須覆寫的方法
    public String getUsername() { return user.getUserName(); }
    public String getPassword() { return user.getPassword(); }
    public boolean isEnabled() { return true; }
    public boolean isAccountNonLocked() { return true; }
    public boolean isCredentialsNonExpired() { return true; }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(user.getAuthority());
    }

    public boolean isAccountNonExpired() {
        if (user.getTrailExpiration() == null) {
            return true;
        }

        return LocalDate.now().isBefore(user.getTrailExpiration());
    }
    
    // 自定義的 public 方法
    public Integer getId() { return user.getUserId(); }
    public UserAuthority getUserAuthority() { return user.getAuthority(); }
    public boolean isPremium() { return user.isPremium(); }
    public LocalDate getTrailExpiration() { return user.getTrailExpiration(); }
}
