package next.controller.user;

import core.mvc.Controller;
import core.mvc.ModelAndView;
import core.mvc.View;
import next.controller.UserSessionUtils;
import next.dao.UserDao;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController implements Controller {
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) {
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		UserDao userDao = new UserDao();
		User user = userDao.findByUserId(userId);
		if (user == null) {
			ModelAndView modelAndView = new ModelAndView(View.createJspView("/user/login.jsp"));
			modelAndView.setAttribute("loginFailed", true);
			return modelAndView;
		}
		if (user.matchPassword(password)) {
			HttpSession session = req.getSession();
			session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user);
			return new ModelAndView(View.createJspView("redirect:/"));
		} else {
			ModelAndView modelAndView = new ModelAndView(View.createJspView("/user/login.jsp"));
			modelAndView.setAttribute("loginFailed", true);
			return modelAndView;
		}
	}
}
