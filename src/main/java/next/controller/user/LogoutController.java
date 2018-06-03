package next.controller.user;

import core.mvc.Controller;
import core.mvc.ModelAndView;
import core.mvc.View;
import next.controller.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController implements Controller {
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		session.removeAttribute(UserSessionUtils.USER_SESSION_KEY);
		return new ModelAndView(View.createJspView("redirect:/"));
	}
}
