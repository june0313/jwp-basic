package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
	public void update(String sql, Object... parameters) throws DataAccessException {
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {	
			setValues(ps, parameters);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object...parameters) throws DataAccessException {
		List<T> result = query(sql, rowMapper, parameters);

		if (result.isEmpty()) {
			return null;
		}

		return result.get(0);
	}
	
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object...parameters) throws DataAccessException {
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			setValues(ps, parameters);		
			return getResult(ps, rowMapper);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	private <T> List<T> getResult(PreparedStatement ps, RowMapper<T> rowMapper) throws DataAccessException {
		try (ResultSet rs = ps.executeQuery()) {
			List<T> result = new ArrayList<>();

			while (rs.next()) {
				result.add(rowMapper.mapRow(rs));
			}

			return result;
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	
	private void setValues(PreparedStatement ps, Object... parameters) throws SQLException {
		for (int i = 0; i < parameters.length; i++) {
			ps.setObject(i + 1,  parameters[i]);
		}
	}
}
