package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.SelectJdbcTemplate;
import next.model.User;

public class UserDao {
	public void insert(User user) throws SQLException {
		new JdbcTemplate() {
			@Override
			protected void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getUserId());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getName());
				ps.setString(4, user.getEmail());
			}
		}.update("INSERT INTO USERS VALUES (?, ?, ?, ?)");
	}

	public void update(User user) throws SQLException {
		new JdbcTemplate() {
			@Override
			protected void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getPassword());
				ps.setString(2, user.getName());
				ps.setString(3, user.getEmail());
				ps.setString(4, user.getUserId());
			}
		}.update("UPDATE users SET password=?, name=?, email=? WHERE userId=?");
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() throws SQLException {
		String sql = "SELECT userId, password, name, email FROM USERS";

		SelectJdbcTemplate jdbcTemplate = new SelectJdbcTemplate() {
			@Override
			protected void setValues(PreparedStatement ps) throws SQLException {
			}

			@Override
			protected Object mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
			}
		};

		return (List<User>) jdbcTemplate.query(sql);
	}

	public User findByUserId(String userId) throws SQLException {
		String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";

		SelectJdbcTemplate jdbcTemplate = new SelectJdbcTemplate() {

			@Override
			protected void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, userId);
			}

			@Override
			protected Object mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
			}
		};

		return (User) jdbcTemplate.queryForObject(sql);
	}
}
