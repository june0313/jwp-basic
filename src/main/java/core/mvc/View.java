package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface View {
	static View createJspView(String viewName) {
		return new JspView(viewName);
	}

	static View createJsonView() {
		return new JsonView();
	}

	void render(Map<String, ?> model, HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
