package next.dao;

import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.model.User;

public class UserDao {
	public void insert(User user) {
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		new JdbcTemplate().update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
	}

	public void update(User user) {
		String sql = "UPDATE users SET password=?, name=?, email=? WHERE userId=?";
		new JdbcTemplate().update(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
	}

	public List<User> findAll() {
		String sql = "SELECT userId, password, name, email FROM USERS";
		RowMapper<User> rowMapper = rs -> new User(
				rs.getString("userId"), 
				rs.getString("password"), 
				rs.getString("name"), 
				rs.getString("email"));
		
		return new JdbcTemplate().query(sql, rowMapper);
	}

	public User findByUserId(String userId) {
		String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
		RowMapper<User> rowMapper = rs -> new User(
				rs.getString("userId"), 
				rs.getString("password"), 
				rs.getString("name"), 
				rs.getString("email"));
		
		return new JdbcTemplate().queryForObject(sql, rowMapper, userId);
	}
}
