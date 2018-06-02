package next.service;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

import java.util.List;

public class QuestionService {
	private QuestionDao questionDao;
	private AnswerDao answerDao;

	public QuestionService() {
		this.questionDao = new QuestionDao();
		this.answerDao = new AnswerDao();
	}

	public Question getQuestion(int id) {
		List<Answer> answers = answerDao.findByQuestionId(id);
		Question question = questionDao.findById(id);
		question.setAnswers(answers);
		return question;
	}
}
