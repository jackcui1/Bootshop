package com.bootshop.controller;

import java.util.List;

import com.bootshop.model.Category;
import com.bootshop.model.User;
import com.bootshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.bootshop.model.Product;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired 
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private StorageFileService storageService;

	@Autowired
	private ItemViewRedisService itemViewRedisService;

	@Autowired
	private UserService userService;

	@RequestMapping("/view/{id}")
	public String viewProduct(@PathVariable("id") int id, Model model, Authentication authentication) {
		Product product = productService.getProductById(id);
		String getFilename=MvcUriComponentsBuilder
				.fromMethodName(ImagesController.class,
						"getFile", product.getImagename()).build().toString();
		product.setAbsolutImagename(getFilename);
		
		model.addAttribute("product", product);

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		if (!username.equals("anonymousUser")) {
			User user = userService.findByUsername(username);
			itemViewRedisService.add(user, product);
		}
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		return "productDetail";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String productList(Model model) {
		List<Product> products = productService.getProductList();

		for(Product product:products){
			String getFilename=MvcUriComponentsBuilder
					.fromMethodName(ImagesController.class,
							"getFile", product.getImagename()).build().toString();
			product.setAbsolutImagename(getFilename);
		}
		List<Category> categories = categoryService.findAll();
		model.addAttribute("count", products.size());
		model.addAttribute("products", products);
		model.addAttribute("categories", categories);
		return "products";
	}

	@RequestMapping(value="/list/subcategory/{id}",method=RequestMethod.GET)
	public String productBySubCategory(@PathVariable("id") Integer id, Model model) {
		List<Product> products = productService.findAllBySubCategoryId(id);

		for(Product product:products){
			String getFilename=MvcUriComponentsBuilder
					.fromMethodName(ImagesController.class,
							"getFile", product.getImagename()).build().toString();
			product.setAbsolutImagename(getFilename);
		}
		List<Category> categories = categoryService.findAll();
		model.addAttribute("count", products.size());
		model.addAttribute("products", products);
		model.addAttribute("categories", categories);
	return "products";
	}




}
