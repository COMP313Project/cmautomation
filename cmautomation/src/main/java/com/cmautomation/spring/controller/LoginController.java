package com.cmautomation.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/*
 * This controller shows Login page to any user
 * */
@Controller
public class LoginController {

	@GetMapping("/showLoginPage")
	public String showLoginPage() {
		//return "login";
		return "login";
	}
	// add request mapping for access denied
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		//return "plain-login";
		return "access-denied";
	}
}
