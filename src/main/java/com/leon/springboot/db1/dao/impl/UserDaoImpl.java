package com.leon.springboot.db1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.leon.springboot.db1.dao.UserDao;
import com.leon.springboot.db1.model.User;
import com.leon.springboot.rowmapper.UserRowMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Component
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User findById(Integer userId) {
		return entityManager.find(User.class, userId);
	}

	@Override
	public List<User> findAll() {
		TypedQuery<User> query = entityManager.createQuery("SELECT * FROM USER", User.class);
		return query.getResultList();
	}

	@Override
	public List<User> findByName(String name) {
		String sqlStr = "SELECT USERID, USERNAME FROM USER WHERE USERNAME like :name";
		// 執行query時，使用StudentRowMapper將數據轉換成Student格式
		List<User> list = jdbcTemplate.query(sqlStr, new UserRowMapper(), new Object[] { "%" + name + "%" });
		return list;
	}

	@Override
	public User create(User user) {
		String sqlStr = "INSERT INTO USER(USERNAME) VALUES(:userName)";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", user.getUserName());

		// 使用自增主鍵時，需搭配KeyHolder取得新增資料的主鍵值
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(sqlStr, new MapSqlParameterSource(map), keyHolder);

		int id = (int) keyHolder.getKey();
		user.setUserId(id);
		return user;
	}

	@Override
	public void save(User user) {
		entityManager.persist(user);
	}

	@Override
	public void update(Integer userId, User user) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String sql = "UPDATE USER (userName) VALUES(?) WHERE userId = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, user.getUserName());
				ps.setInt(2, userId);
				return ps;
			}
		});
	}

	@Override
	public void delete(User user) {
		entityManager.remove(user);
	}

}
