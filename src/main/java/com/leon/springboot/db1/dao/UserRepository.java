package com.leon.springboot.db1.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.leon.springboot.db1.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	@Query(value = "SELECT * FROM USER WHERE USERNAME = ?1",
			nativeQuery = true)
	User findByUserName(String name);
	
	// nativaQuery = true -> 一般SQL語法；false -> JPQL
	@Query(value = "SELECT USERID, USERNAME FROM USER WHERE USERID = ?", nativeQuery = true)
	User find(Integer userId);

}
