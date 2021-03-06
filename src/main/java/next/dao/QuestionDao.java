package next.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.model.Question;

import java.util.List;

public class QuestionDao {
    private JdbcTemplate jdbcTemplate;

    public QuestionDao() {
        this.jdbcTemplate = new JdbcTemplate();
    }

    public List<Question> findAll() {
        return jdbcTemplate.query("SELECT questionId, writer, title, contents, countOfAnswer, createdDate FROM QUESTIONS", questionMapper());
    }

    public Question findById(int id) {
        return jdbcTemplate.queryForObject("SELECT questionId, writer, title, contents, countOfAnswer, createdDate FROM questions WHERE questionId = ?", questionMapper(), id);
    }

    private RowMapper<Question> questionMapper() {
        return rs -> {
            Question question = new Question();
            question.setId(rs.getInt("questionId"));
            question.setWriter(rs.getString("writer"));
            question.setTitle(rs.getString("title"));
            question.setContents(rs.getString("contents"));
            question.setCountOfAnswer(rs.getInt("countOfAnswer"));
            question.setCreatedDate(rs.getDate("createdDate"));
            return question;
        };
    }
}
