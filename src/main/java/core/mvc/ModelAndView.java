package core.mvc;

import com.google.common.collect.Maps;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ModelAndView {
	private Map<String, Object> modelMap;
	private View view;

	public ModelAndView(View view) {
		this.modelMap = Maps.newHashMap();
		this.view = view;
	}

	public void setAttribute(String name, Object value) {
		modelMap.put(name, value);
	}

	public void render(HttpServletRequest request, HttpServletResponse response) throws Exception {
		view.render(modelMap, request, response);
	}
}
