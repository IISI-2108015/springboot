package com.leon.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leon.springboot.db1.model.User;
import com.leon.springboot.security.config.UserAuthority;
import com.leon.springboot.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(value = "http://localhost:5173") // 接受跨域請求
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable Integer id) {
		User user = userService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@GetMapping
	public ResponseEntity<List<User>> getUser() {
		List<User> list = userService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User temp = userService.findByUserName(user.getUserName());
		if(temp != null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}else {
			user.setAuthority(UserAuthority.NORMAL.toString());
			userService.save(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(user);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
		userService.update(id, user);
		user.setUserId(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
		try {
			userService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Delete User");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Delete Failed");
		}
	}
	
}
