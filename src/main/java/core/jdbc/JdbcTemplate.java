package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
	public void update(String query, PreparedStatementSetter pss) throws DataAccessException {
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			pss.setValues(ps);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	
	public <T> List<T> query(String sql, PreparedStatementSetter pss, RowMapper<T> rowMapper) throws DataAccessException {
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			pss.setValues(ps);
			return getResult(ps, rowMapper);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	
	public <T> T queryForObject(String sql, PreparedStatementSetter pss, RowMapper<T> rowMapper) throws DataAccessException {
		List<T> result = query(sql, pss, rowMapper);
		
		if (result.isEmpty()) {
			return null;
		}
		
		return result.get(0);
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
}
