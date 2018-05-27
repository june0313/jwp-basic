package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import next.model.User;

public class UserDao {
	public void insert(User user) {
		new JdbcTemplate().update("INSERT INTO USERS VALUES (?, ?, ?, ?)", new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getUserId());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getName());
				ps.setString(4, user.getEmail());
			}
		});
	}

	public void update(User user) {
		new JdbcTemplate().update("UPDATE users SET password=?, name=?, email=? WHERE userId=?", new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getPassword());
				ps.setString(2, user.getName());
				ps.setString(3, user.getEmail());
				ps.setString(4, user.getUserId());
			}
		});
	}

	public List<User> findAll() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();

		return (List<User>) jdbcTemplate.query("SELECT userId, password, name, email FROM USERS", new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
			}
		}, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
			}
		});
	}

	public User findByUserId(String userId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();

		return jdbcTemplate.queryForObject("SELECT userId, password, name, email FROM USERS WHERE userid=?", new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, userId);
			}
		}, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
			}
		});
	}
}
