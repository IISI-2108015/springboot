package com.leon.springboot.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.leon.springboot.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {// 在問號後面加數字，指定要載入第幾個參數的值
	
	// nativaQuery = true -> 一般SQL語法；false -> JPQL
	@Query(value = "SELECT USERID, USERNAME FROM USER WHERE USERID = ?", nativeQuery = true)
	User find(Integer userId);

}
