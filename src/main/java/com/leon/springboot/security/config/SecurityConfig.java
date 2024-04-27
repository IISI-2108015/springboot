package com.leon.springboot.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.leon.springboot.security.component.JwtAuthenticationFilter;

/**
 * 建立 SecurityFilterChain 的過程中，透過呼叫「HttpSecurity」物件的方法，可幫助我們配置 API 的授權規則。
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
			BCryptPasswordEncoder passwordEncoder) {
		var provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter authFilter)
			throws Exception {
		http.authorizeHttpRequests(registry -> registry.requestMatchers(HttpMethod.POST, "/user").permitAll() // 全部同意
				.requestMatchers(HttpMethod.GET, "/user/?*").hasAnyAuthority("ADMIN", "NORMAL") // 只有ADMIN、NORMAL可以
				.requestMatchers(HttpMethod.GET, "/user").hasAuthority("ADMIN") // 只有ADMIN可以
				.requestMatchers(HttpMethod.POST, "/loginAuth").permitAll().anyRequest().permitAll() // 全部同意
		).csrf(AbstractHttpConfigurer::disable).addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}