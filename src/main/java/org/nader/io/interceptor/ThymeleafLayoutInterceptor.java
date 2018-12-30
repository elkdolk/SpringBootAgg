package org.nader.io.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/************************************************************************
 * Gets the original view name returned from controllers and replaces it *
 * with layout name. The original view is placed in the model as a view *
 * variable ,and it can be used in the layout file.                     *
 ************************************************************************/
@Component
public class ThymeleafLayoutInterceptor extends HandlerInterceptorAdapter {

	private static final String DEFAULT_LAYOUT = "layouts/layout";
	private static final String DEFAULT_VIEW_ATTRIBUTE_NAME = "view";
	
	// postHandle is executed before rendering the view 
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView modelAndView) throws Exception {
		
		if(modelAndView == null || !modelAndView.hasView()){
			return;
		}
		String originalViewName = modelAndView.getViewName();
		if(isRedirectOrforward(originalViewName)){
			return;
		}
		modelAndView.setViewName(DEFAULT_LAYOUT);
		modelAndView.addObject(DEFAULT_VIEW_ATTRIBUTE_NAME, originalViewName);
	}
	
	private boolean isRedirectOrforward(String viewName) {
		return viewName.startsWith("redirect:") || viewName.startsWith("forward:");
	}
}

