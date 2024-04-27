package com.leon.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leon.springboot.db1.dao.UserDao;
import com.leon.springboot.db1.dao.UserRepository;
import com.leon.springboot.db1.model.User;
import com.leon.springboot.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findById(Integer userId) {

//		return userDao.findById(userId);

		return userRepository.find(userId);
	}
	
	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public List<User> findAll() {

//		return userDao.findAll();

		List<User> list = new ArrayList<User>();
		Iterable<User> users = userRepository.findAll();
		users.forEach(list::add);
		return list;
	}

	@Override
	public List<User> findByName(String userName) {
		return userDao.findByName(userName);
	}

	@Override
	@Transactional(value = "db1TransactionManager")
	public void save(User user) {
//		userDao.save(user);
		userRepository.save(user);
	}

	@Override
	@Transactional(value = "db1TransactionManager")
	public void update(Integer userId, User user) {

//		userDao.update(userId, user);

		User oriUser = userRepository.find(userId);
		oriUser.setUserName(user.getUserName());
		userRepository.save(oriUser);

	}

	@Override
	@Transactional(value = "db1TransactionManager", rollbackFor = Exception.class)
	public void delete(Integer userId) throws Exception {

//		User user = userDao.findById(userId);
//		userDao.delete(user);

		userRepository.deleteById(userId);
		throw new Exception("exception text");

	}

}
