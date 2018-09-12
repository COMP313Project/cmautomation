package com.cmautomation.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
	@GetMapping("/")
	public String showHome() {
		return "home";// this is the jsp file that will be rendered
	}
	
	// add request mapping for leaders
	
	@GetMapping("/leaders")
	public String showLeaders() {
		return "leaders"; // the jsp file that will be rendered
	}

}
