package com.leon.springboot.service;

import java.util.List;

import com.leon.springboot.db1.model.User;

public interface UserService {
	
	User findById(Integer userId);
	
	User findByUserName(String userName);
	
	List<User> findAll();
	
	List<User> findByName(String userName);
	
	void save(User user);
	
	void update(Integer userId, User user);
	
	void delete(Integer userId) throws Exception ;

}
