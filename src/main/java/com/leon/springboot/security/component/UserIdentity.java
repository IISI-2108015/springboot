package com.leon.springboot.security.component;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.leon.springboot.db1.model.UserAuth;
import com.leon.springboot.security.config.UserAuthority;
import com.leon.springboot.security.model.AppUserDetails;

/**
 * 取得該人的資訊
 */
@Component
public class UserIdentity {

	private AppUserDetails getUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();

		return "anonymousUser".equals(principal) ? new AppUserDetails(new UserAuth()) : (AppUserDetails) principal;
	}

	public Integer getId() {
		return getUserDetails().getId();
	}

	public String getUsername() {
		return getUserDetails().getUsername();
	}

	public UserAuthority getUserAuthority() {
		return getUserDetails().getUserAuthority();
	}

	public boolean isPremium() {
		return getUserDetails().isPremium();
	}

}
