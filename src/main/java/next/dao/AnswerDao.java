package next.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.model.Answer;

import java.util.List;

public class AnswerDao {
	private JdbcTemplate jdbcTemplate;

	public AnswerDao() {
		this.jdbcTemplate = new JdbcTemplate();
	}

	public List<Answer> findByQuestionId(int questionId) {
		final String sql = "SELECT answerid, writer, createddate, contents FROM answers WHERE questionid = ?";
		return jdbcTemplate.query(sql, answerMapper(), questionId);
	}

	private RowMapper<Answer> answerMapper() {
		return rs -> {
			Answer answer = new Answer();
			answer.setId(rs.getInt("answerId"));
			answer.setWriter(rs.getString("writer"));
			answer.setContents(rs.getString("contents"));
			answer.setCreatedDate(rs.getDate("createdDate"));
			return answer;
		};
	}
}
