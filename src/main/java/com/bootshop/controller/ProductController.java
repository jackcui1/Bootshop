package com.bootshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.bootshop.model.FirstCategory;
import com.bootshop.model.Product;
import com.bootshop.service.FirstCategoryService;
import com.bootshop.service.ProductService;
import com.bootshop.service.StorageFileService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired 
	private ProductService productService;
	
	@Autowired
	private StorageFileService storageService;
	
	@Autowired
	private FirstCategoryService firstCategoryService;
	
	@RequestMapping("/view/")
	public String viewProduct(@RequestParam("id") int id, Model model) {
		Product product = productService.getProductById(id);
		String getFilename=MvcUriComponentsBuilder
				.fromMethodName(ImageController.class,
						"getFile", product.getImagename()).build().toString();
		product.setAbsolutImagename(getFilename);
		
		model.addAttribute("product", product);
		
		return "restproductDetail";
	}
	
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String productList(Model model) {
		return "products";
	}
	/*
	@RequestMapping(value="/list/firstcategory/{id}",method=RequestMethod.GET)
	public String productListByfirstCategoryId(@PathVariable int id,Model model){
		List<Product> products = productService.getProductListByFirstCategoryId(id);
		addProductImagePath(products);
		model.addAttribute("products", products);
		List<FirstCategory> firstCategories =firstCategoryService.findAll();
		model.addAttribute("firstCategories",firstCategories);
		return "products";
	}
	
	@RequestMapping(value="/list/secondcategory/{id}",method=RequestMethod.GET)
	public String productListBySecondCategoryId(@PathVariable int id,Model model){
		List<Product> products = productService.getProductListByFirstCategoryId(id);
		addProductImagePath(products);
		model.addAttribute("products", products);
		List<FirstCategory> firstCategories =firstCategoryService.findAll();
		model.addAttribute("firstCategories",firstCategories);
		return "products";
	}
	*/
	public void addProductImagePath(List<Product> products){
		for(Product product:products){
			String getFilename=MvcUriComponentsBuilder
					.fromMethodName(ImageController.class,
							"getFile", product.getImagename()).build().toString();
			product.setAbsolutImagename(getFilename);
		}
	}

}
