package com.leon.springboot.service;

import java.util.List;

import com.leon.springboot.model.User;

public interface UserService {
	
	User findById(Integer userId);
	
	List<User> findAll();
	
	List<User> findByName(String userName);
	
	void save(User user);
	
	void update(Integer userId, User user);
	
	void delete(Integer userId);

}
