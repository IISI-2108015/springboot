package com.leon.springboot.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leon.springboot.db1.model.User;
import com.leon.springboot.security.model.LoginResponse;
import com.leon.springboot.security.service.TokenService;

@RestController
@CrossOrigin(value = "http://localhost:5173") // 接受跨域請求
public class LoginController {

	@Autowired
	private TokenService tokenService;

	/**
	 * 登入取得token
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/loginAuth") // 不可以用/login
	public ResponseEntity<LoginResponse> login(@RequestBody User user) {
		LoginResponse res = tokenService.createToken(user);
		return ResponseEntity.ok(res);
	}

}
