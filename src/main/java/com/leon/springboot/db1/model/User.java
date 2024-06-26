package com.leon.springboot.db1.model;

import java.time.LocalDate;

import com.leon.springboot.security.config.UserAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private Integer userId;

	@Column(name = "userName")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="authority")
	private String authority;
	
	@Transient
    private boolean enabled = true;
	@Transient
    private boolean premium = false;
	@Transient
    private LocalDate trailExpiration;
	
}
