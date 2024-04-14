package com.leon.springboot.db1.dao;

import java.util.List;

import com.leon.springboot.db1.model.User;

public interface UserDao {
	
	User findById(Integer userId);
	
	List<User> findAll();
	
	List<User> findByName(String userName);
	
	User create(User user);
	
	void save(User user);
	
	void update(Integer userId, User user);
	
	void delete(User user);

}
