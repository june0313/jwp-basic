package core.web.mapping;

import com.google.common.collect.Maps;
import next.controller.*;

import java.util.Map;

public class RequestMapping {
    private static final Map<String, Controller> controllers;

    static {
        controllers = Maps.newHashMap();
        controllers.put("/", new HomeController());
        controllers.put("/users", new ListUserController());
        controllers.put("/users/loginForm", new ForwardController("/user/login.jsp"));
        controllers.put("/users/login", new LoginController());
        controllers.put("/users/form", new ForwardController("/user/form.jsp"));
        controllers.put("/users/create", new CreateUserController());
        controllers.put("/users/logout", new LogoutController());
        controllers.put("/users/profile", new ProfileController());
        controllers.put("/users/updateForm", new UpdateUserFormController());
        controllers.put("/users/update", new UpdateUserController());
    }


    public static Controller findController(String requestUrl) {
        return controllers.get(requestUrl);
    }


}
