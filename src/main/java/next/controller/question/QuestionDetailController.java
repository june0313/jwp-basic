package next.controller.question;

import core.mvc.Controller;
import next.model.Question;
import next.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestionDetailController implements Controller {
	private QuestionService questionService;

	public QuestionDetailController() {
		questionService = new QuestionService();
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int questionId = Integer.valueOf(req.getParameter("questionId"));
		Question question = questionService.getQuestion(questionId);
		req.setAttribute("question", question);
		return "/qna/detail.jsp";
	}
}
