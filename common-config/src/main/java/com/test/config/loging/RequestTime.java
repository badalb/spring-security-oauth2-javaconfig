package com.test.config.loging;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class RequestTime {
	
	public String getRequestStringToLog(HttpServletRequest request) {
		String uri = request.getRequestURI();
		if (!uri.contains("/resources/") 
				&& !uri.contains("/bundle/") 
				&& !uri.contains("/api/v1/")
				) {
			long startTime = request.getAttribute("startTime") == null ? 0 : (Long) request.getAttribute("startTime");
			request.removeAttribute("startTime");
			long endTime = System.currentTimeMillis();
			long handlingTime = endTime - startTime;
			StringBuffer printRequest = new StringBuffer();
			String mode = "Visitor" + " [" + request.getSession().getId() + "]";
			if (isUserLoggedIn()) {
				mode = getUserName() + " [" + request.getSession().getId()
						+ "]";
			}
			printRequest.append(mode);
			printRequest.append(" [" + request.getMethod()+ "]");
			printRequest.append(" [" + uri + "]");
			Enumeration<String> pnames = request.getParameterNames();
			StringBuilder allParams = new StringBuilder();
			while (pnames.hasMoreElements()) {
				String pname = pnames.nextElement();
				StringBuilder result = getValues(request, pname);
				allParams.append(result.toString());
				allParams.append("|");
			}
			if(allParams.length() > 0) {
				printRequest.append(" [" + allParams.toString()+ "]");
			}
			printRequest.append(" [" + handlingTime + "ms]");
			printRequest.append(" ["
					+ handlingTimePerformanceStatus(handlingTime) + "]");
			return printRequest.toString();
		}
		return "";
	}
	
	private boolean isUserLoggedIn() {
		if (SecurityContextHolder.getContext() != null) {
			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();
			if (authentication != null)
				return true;
		}
		return false;
	}

	
	private StringBuilder getValues(HttpServletRequest request, String pname) {
		boolean mask = false;		
		if (pname.contains("password") || pname.contains("Password")
				|| pname.contains("cardNumber") || pname.contains("cvv")
				|| pname.contains("merchantAccessKey")
				|| pname.contains("displayCardNumber")) {
			mask = true;
		}
		
		StringBuilder result = new StringBuilder(pname);
		String pvalues[] = request.getParameterValues(pname);
		result.append('=');
		for (int i = 0; i < pvalues.length; i++) {
			if (i > 0)
				result.append(", ");
			String val = null;
			if(mask) {
				val = "xxxx";
			} else {
				val = pvalues[i];
			}
			
			val = val.replaceAll(System.getProperty("line.separator"), "");
			result.append(val);
		}
		return result;
	}

	private String getUserName() {
		String username = null;
		if (SecurityContextHolder.getContext() != null) {
			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();
			if (authentication != null)
				username = authentication.getName();
		}
		return username;
	}
	
	private String handlingTimePerformanceStatus(long handlingTime) {
		String status = "OK";
		if (handlingTime > 2000 && handlingTime < 10000) {
			status = "SLOW";
		} else if (handlingTime > 10000) {
			status = "NOK";
		}
		return status;
	}

}
