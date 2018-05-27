package next.dao;

import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import next.model.User;

public class UserDao {
	public void insert(User user) {
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		PreparedStatementSetter pss = ps -> {
			ps.setString(1, user.getUserId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getEmail());
		};

		new JdbcTemplate().update(sql, pss);
	}

	public void update(User user) {
		String sql = "UPDATE users SET password=?, name=?, email=? WHERE userId=?";
		PreparedStatementSetter pss = ps -> {
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getUserId());
		};
		
		new JdbcTemplate().update(sql, pss);
	}

	public List<User> findAll() {
		String sql = "SELECT userId, password, name, email FROM USERS";
		PreparedStatementSetter pss = ps -> {};
		RowMapper<User> rowMapper = rs -> new User(
				rs.getString("userId"), 
				rs.getString("password"), 
				rs.getString("name"), 
				rs.getString("email"));
		
		return new JdbcTemplate().query(sql, pss, rowMapper);
	}

	public User findByUserId(String userId) {
		String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
		PreparedStatementSetter pss = ps -> ps.setString(1, userId);
		RowMapper<User> rowMapper = rs -> new User(
				rs.getString("userId"), 
				rs.getString("password"), 
				rs.getString("name"), 
				rs.getString("email"));
		
		return new JdbcTemplate().queryForObject(sql, pss, rowMapper);
	}
}
