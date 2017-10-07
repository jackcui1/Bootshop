package com.minibookstore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
	
	@Autowired
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = { "/welcome" })
	public String welcome() {
		return "welcome";
	}

	@RequestMapping(value="/login")
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = "/403")
	public String error403() {
		return "403";
	}
}