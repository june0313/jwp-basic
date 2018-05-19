package core.web.servlet;

import next.controller.Controller;
import core.web.mapping.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
    private static final String REDIRECT_PREFIX = "redirect:";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        String requestUri = req.getRequestURI();

        log.debug("Method : {}, Request URI : {}", method, requestUri);

        Controller controller = RequestMapping.findController(requestUri);

        if (controller == null) {
            resp.sendError(404, "Fot found : " + method + " " + requestUri);
            return;
        }

        try {
            String viewName = controller.execute(req, resp);
            move(req, resp, viewName);
        } catch (Exception e) {
            log.error("Exception : {}", e.getMessage(), e);
            throw new ServletException(e.getMessage());
        }
    }

    private void move(HttpServletRequest req, HttpServletResponse resp, String view) throws IOException, ServletException {
        if (view.startsWith(REDIRECT_PREFIX)) {
            resp.sendRedirect(view.substring(REDIRECT_PREFIX.length()));
        } else {
            RequestDispatcher rd = req.getRequestDispatcher(view);
            rd.forward(req, resp);
        }
    }

}
