package com.minibookstore.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.minibookstore.model.User;
import com.minibookstore.repository.UserRepository;

@Controller
public class WelcomeController {
	
	@Autowired
	 private UserRepository userRepository;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = { "/welcome" })
	public String welcome() {
		return "welcome";
	}

	@RequestMapping(name="/login")
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = "/403")
	public String error403() {
		return "403";
	}
}