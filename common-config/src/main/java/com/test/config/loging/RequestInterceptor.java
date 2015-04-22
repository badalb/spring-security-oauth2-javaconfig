package com.test.config.loging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory
			.getLogger(RequestInterceptor.class);

	@Autowired
	RequestTime loggerHelper;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String requestStringToLog = loggerHelper.getRequestStringToLog(request);
		if(!requestStringToLog.isEmpty())
			logger.info(requestStringToLog);
		super.postHandle(request, response, handler, modelAndView);
	}

}
