package com.bootshop.controller;

import com.bootshop.model.Category;
import com.bootshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CategoryService categoryService;
		
	@RequestMapping
	public String getCart(Model model){
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		return "cart";
	}
}
