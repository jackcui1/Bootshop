package com.minibookstore.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

	@RequestMapping("/")
	public String welcome() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
}