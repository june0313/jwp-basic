package next.controller.user;

import core.mvc.Controller;
import core.mvc.ModelAndView;
import core.mvc.View;
import next.dao.UserDao;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileController implements Controller {
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) {
		String userId = req.getParameter("userId");
		UserDao userDao = new UserDao();
		User user = userDao.findByUserId(userId);
		if (user == null) {
			throw new NullPointerException("사용자를 찾을 수 없습니다.");
		}
		req.setAttribute("user", user);

		ModelAndView modelAndView = new ModelAndView(View.createJspView("/user/profile.jsp"));
		modelAndView.setAttribute("user", user);
		return modelAndView;
	}
}
