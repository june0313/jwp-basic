package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class SelectJdbcTemplate {
	@SuppressWarnings("rawtypes")
	public List query(String sql) throws SQLException {
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			setValues(ps);
			return getResult(ps);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public Object queryForObject(String sql) throws SQLException {
		List result = query(sql);
		
		if (result.isEmpty()) {
			return null;
		}
		
		return result.get(0);
	}

	@SuppressWarnings("rawtypes")
	private List getResult(PreparedStatement ps) throws SQLException {
		try (ResultSet rs = ps.executeQuery()) {
			List<Object> result = new ArrayList<>();
			
			while (rs.next()) {
				result.add(mapRow(rs));
			}
			
			return result;
		}
	}
	
	protected abstract void setValues(PreparedStatement ps) throws SQLException;
	
	protected abstract Object mapRow(ResultSet rs) throws SQLException;
}
