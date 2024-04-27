package com.leon.springboot.security.config;

import org.springframework.security.core.GrantedAuthority;

public enum UserAuthority implements GrantedAuthority {
	ADMIN,
	NORMAL;
	
	@Override
	public String getAuthority() {
		return name();
	}
}
