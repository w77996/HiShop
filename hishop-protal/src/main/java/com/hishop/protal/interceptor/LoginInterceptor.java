package com.hishop.protal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hishop.common.util.CookieUtils;
import com.hishop.pojo.TbUser;
import com.hishop.protal.service.UserService;
import com.hishop.protal.service.impl.UserServiceImpl;

public class LoginInterceptor implements HandlerInterceptor {
	@Autowired
	public UserServiceImpl userService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token =	CookieUtils.getCookieValue(request, "TT_TOKEN");
		TbUser user = userService.getUserByToken(token);
		if(null==user){
			response.sendRedirect(userService.SSO_BASE_URl + userService.SSO_PAGE_LOGIN+"?redirect="+request.getRequestURI());
		
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
