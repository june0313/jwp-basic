package next.controller.user;

import core.mvc.Controller;
import core.mvc.ModelAndView;
import core.mvc.View;
import next.controller.UserSessionUtils;
import next.dao.UserDao;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateFormUserController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) {
		String userId = req.getParameter("userId");
		UserDao userDao = new UserDao();
		User user = userDao.findByUserId(userId);
		if (!UserSessionUtils.isSameUser(req.getSession(), user)) {
			throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
		}

		ModelAndView modelAndView = new ModelAndView(View.createJspView("/user/updateForm.jsp"));
		modelAndView.setAttribute("user", user);
		return modelAndView;
	}
}
