package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.Controller;
import next.dao.UserDao;
import next.model.User;

public class CreateUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(CreateUserController.class);

    private UserDao userDao = new UserDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"),
                req.getParameter("email"));
        log.debug("User : {}", user);

        userDao.insert(user);
        return "redirect:/";
    }
}
