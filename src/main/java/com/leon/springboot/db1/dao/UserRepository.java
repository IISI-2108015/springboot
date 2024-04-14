package com.leon.springboot.db1.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.leon.springboot.db1.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	// nativaQuery = true -> 一般SQL語法；false -> JPQL
	@Query(value = "SELECT USERID, USERNAME FROM USER WHERE USERID = ?", nativeQuery = true)
	User find(Integer userId);

}
