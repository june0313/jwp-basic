package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.google.common.collect.Lists;

import core.jdbc.ConnectionManager;
import core.jdbc.JdbcTemplate;
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

	public List<User> findAll() throws SQLException {
		List<User> userList = Lists.newArrayList();
		String sql = "SELECT userId, password, name, email FROM USERS";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet resultSet = pstmt.executeQuery()) {

			while (resultSet.next()) {
				User user = new User(resultSet.getString("userId"), resultSet.getString("password"),
						resultSet.getString("name"), resultSet.getString("email"));

				userList.add(user);
			}
		}

		return userList;
	}

	public User findByUserId(String userId) throws SQLException {
		String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, userId);
			User user = null;

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
							rs.getString("email"));
				}
			}

			return user;
		}
	}
}
