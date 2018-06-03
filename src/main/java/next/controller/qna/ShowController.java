package next.controller.qna;

import core.mvc.Controller;
import core.mvc.ModelAndView;
import core.mvc.View;
import next.dao.AnswerDao;
import next.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowController implements Controller {
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) {
		Long questionId = Long.parseLong(req.getParameter("questionId"));
		QuestionDao questionDao = new QuestionDao();
		AnswerDao answerDao = new AnswerDao();

		ModelAndView modelAndView = new ModelAndView(View.createJspView("/qna/show.jsp"));
		modelAndView.setAttribute("question", questionDao.findById(questionId));
		modelAndView.setAttribute("answers", answerDao.findAllByQuestionId(questionId));
		return modelAndView;
	}
}
