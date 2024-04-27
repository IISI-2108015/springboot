package com.leon.springboot.db1.model;

import java.time.LocalDate;

import com.leon.springboot.security.config.UserAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserAuth {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private Integer userId;

	@Column(name = "userName")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="authority")
	private UserAuthority authority;
	
    private boolean enabled = true;
    private boolean premium = false;
    private LocalDate trailExpiration;
	
}
