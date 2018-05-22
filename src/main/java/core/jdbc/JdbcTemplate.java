package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class JdbcTemplate {
	public void update(String query) throws SQLException {
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			setValues(ps);
			ps.executeUpdate();
		}
	}

	abstract protected void setValues(PreparedStatement ps) throws SQLException;

}
