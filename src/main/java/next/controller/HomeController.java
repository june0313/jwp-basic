package next.controller;

import core.mvc.Controller;
import next.dao.QuestionDao;
import next.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements Controller {
    private UserDao userDao = new UserDao();
    private QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setAttribute("users", userDao.findAll());
        req.setAttribute("questions", questionDao.findAll());
        return "home.jsp";
    }
}
