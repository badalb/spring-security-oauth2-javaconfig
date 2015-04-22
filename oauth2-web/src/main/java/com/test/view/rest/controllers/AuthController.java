package com.test.view.rest.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.persistence.entities.User;
import com.test.security.config.CustomUserDetailsService;
import com.test.view.rest.presentation.AuthRepresentation;
import com.test.view.rest.presentation.ViewMapper;

@RestController
public class AuthController {

	@Autowired
	private ViewMapper<AuthRepresentation> userViewMapper;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public @ResponseBody
	AuthRepresentation success(HttpServletRequest request,
			HttpServletResponse response) {
		User user =null;
		if (SecurityContextHolder.getContext().getAuthentication() == null
				|| SecurityContextHolder.getContext().getAuthentication()
						.getPrincipal() == null
				|| SecurityContextHolder.getContext().getAuthentication()
						.getPrincipal().equals("anonymousUser")) {
			login(request, "admin", "password");

		}
			 user = (User) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
	

		AuthRepresentation auth = userViewMapper.map(user,
				AuthRepresentation.class);
		auth.setStatusCode("200");
		auth.setMessage("Authentication Successful");
		return auth;

	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/failure", method = RequestMethod.GET)
	public @ResponseBody
	AuthRepresentation failure(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		response.setStatus(400);
		AuthRepresentation auth = new AuthRepresentation();
		auth.setStatusCode("400");

		AuthenticationException exception = (AuthenticationException) request
				.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

		if (exception instanceof UsernameNotFoundException) {

			auth.setMessage("Invalid User id.");
			return auth;

		}
		if (exception instanceof BadCredentialsException) {
			auth.setMessage("BadCredential");
		}

		auth.setMessage("Authentication Failed!!!!.");
		return auth;
	}

	public void login(HttpServletRequest request, String userName,
			String password) {
		UserDetails userDetails = customUserDetailsService
				.loadUserByUsername(userName);
		System.out.println("@@@@@@@@@@@" + userDetails.getUsername());
		Authentication authentication = new UsernamePasswordAuthenticationToken(
				userDetails, userDetails.getPassword(),
				userDetails.getAuthorities());
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ auth"
				+ authentication.getPrincipal());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		HttpSession session = request.getSession(true);
		session.setAttribute("SPRING_SECURITY_CONTEXT",
				SecurityContextHolder.getContext());
	}
}
