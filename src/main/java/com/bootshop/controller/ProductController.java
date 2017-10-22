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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.bootshop.model.Product;
import com.bootshop.service.ProductService;
import com.bootshop.service.StorageFileService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired 
	private ProductService productService;
	
	@Autowired
	private StorageFileService storageService;
	
	@RequestMapping("/view/{id}")
	public String viewProduct(@PathVariable("id") int id, Model model) {
		Product product = productService.getProductById(id);
		String getFilename=MvcUriComponentsBuilder
				.fromMethodName(ProductController.class,
						"getFile", product.getImagename()).build().toString();
		product.setAbsolutImagename(getFilename);
		
		model.addAttribute("product", product);
		
		return "productDetail";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String productList(Model model) {
		List<Product> products = productService.getProductList();
		for(Product product:products){
			String getFilename=MvcUriComponentsBuilder
					.fromMethodName(ProductController.class,
							"getFile", product.getImagename()).build().toString();
			product.setAbsolutImagename(getFilename);
		}
		model.addAttribute("products", products);
		return "products";
	}
	
	@GetMapping("/imgfiles/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.loadFile(filename);
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

}
