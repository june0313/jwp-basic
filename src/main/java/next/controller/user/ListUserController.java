package next.controller.user;

import core.mvc.Controller;
import core.mvc.ModelAndView;
import core.mvc.View;
import next.controller.UserSessionUtils;
import next.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListUserController implements Controller {
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if (!UserSessionUtils.isLogined(req.getSession())) {
			return new ModelAndView(View.createJspView("redirect:/users/loginForm"));
		}

		UserDao userDao = new UserDao();

		ModelAndView modelAndView = new ModelAndView(View.createJspView("/user/list.jsp"));
		modelAndView.setAttribute("users", userDao.findAll());
		return modelAndView;
	}
}
