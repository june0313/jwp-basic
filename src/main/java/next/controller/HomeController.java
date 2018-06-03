package next.controller;

import core.mvc.Controller;
import core.mvc.ModelAndView;
import core.mvc.View;
import next.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements Controller {
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) {
		QuestionDao questionDao = new QuestionDao();
		ModelAndView modelAndView = new ModelAndView(View.createJspView("home.jsp"));
		modelAndView.setAttribute("questions", questionDao.findAll());

		return modelAndView;
	}
}
