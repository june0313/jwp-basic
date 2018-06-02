package core.mvc;

import java.util.HashMap;
import java.util.Map;

import next.controller.question.QuestionDetailController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.controller.CreateUserController;
import next.controller.HomeController;
import next.controller.ListUserController;
import next.controller.LoginController;
import next.controller.LogoutController;
import next.controller.ProfileController;
import next.controller.UpdateFormUserController;
import next.controller.UpdateUserController;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    private Map<String, Controller> mappings = new HashMap<>();

    void initMapping() {
        put("/", new HomeController());
        put("/users/form", new ForwardController("/user/form.jsp"));
        put("/users/loginForm", new ForwardController("/user/login.jsp"));
        put("/users", new ListUserController());
        put("/users/login", new LoginController());
        put("/users/profile", new ProfileController());
        put("/users/logout", new LogoutController());
        put("/users/create", new CreateUserController());
        put("/users/updateForm", new UpdateFormUserController());
        put("/users/update", new UpdateUserController());
        put("/questions/detail", new QuestionDetailController());

        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String url) {
        return mappings.get(url);
    }

    void put(String url, Controller controller) {
        mappings.put(url, controller);
    }
}
