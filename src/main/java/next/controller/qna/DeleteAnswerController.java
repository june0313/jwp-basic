package next.controller.qna;

import core.mvc.Controller;
import core.mvc.ModelAndView;
import core.mvc.View;
import next.dao.AnswerDao;
import next.model.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAnswerController implements Controller {
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) {
		Long answerId = Long.parseLong(req.getParameter("answerId"));
		AnswerDao answerDao = new AnswerDao();

		answerDao.delete(answerId);

		ModelAndView modelAndView = new ModelAndView(View.createJsonView());
		modelAndView.setAttribute("result", Result.ok());
		return modelAndView;
	}
}
