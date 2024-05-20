package com.leon.springboot.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.leon.springboot.db1.model.User;
import com.leon.springboot.security.config.UserAuthority;

public class UserRowMapper implements RowMapper<User> {

	// 實作RowMapper將數據轉換成Java Object，在執行query()時才會幫我們轉換資料
	@Override
	public User mapRow(ResultSet resultSet, int i) throws SQLException {
		// ResultSet：從資料庫中取得的數據
		// i：目前取到第幾筆數據
		User user = new User();
		// 將resultSet的數據轉換到Student中
		user.setUserId(resultSet.getInt("userId"));
		user.setUserName(resultSet.getString("userName"));
		user.setPassword(resultSet.getString("password"));
		user.setAuthority(resultSet.getString("authority"));
		return user;
	}

}