package com.minibookstore.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response){
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null){
			new SecurityContextLogoutHandler().logout(request,response,auth);
		}
		return "redirect:/login?logout";
	}
}