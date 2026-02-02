package com.mycom.myweb;

import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {	
	
		
		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/board/login");
            return false;
        }
		return true;
	}
}
