package com.test.view.rest.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/api/v1/", "" })
public class HomeController {


	@RequestMapping(value = "/secured/hello", method = RequestMethod.GET)
	public String hello() {
		return "Hello World";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "Hello World";
	}

}
