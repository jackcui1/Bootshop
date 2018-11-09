package com.bootshop.controller;


import java.util.List;


import com.bootshop.common.CommonConstants;
import com.bootshop.model.Article;
import com.bootshop.model.Category;
import com.bootshop.service.ArticleService;
import com.bootshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.bootshop.model.Product;
import com.bootshop.service.ProductService;
import com.bootshop.service.StorageFileService;

@Controller
public class HomeController {
	
	@Autowired 
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private StorageFileService storageService;
	
	@RequestMapping(value="/login")
	public String bootshoplogin(Model model) {
		return "login";
	}
	
	@RequestMapping("/")
	public String home(Model model) {
		List<Product> products = productService.getProductList();
		for(Product product:products){
			String getFilename=MvcUriComponentsBuilder
					.fromMethodName(ImagesController.class,
							"getFile", product.getImagename()).build().toString();
			product.setAbsolutImagename(getFilename);
		}
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		List<Article> articles = articleService.findByType(CommonConstants.ARTICLE_TYPE_CAROUSEL);
		articles.forEach((article) -> {
			String getFilename=MvcUriComponentsBuilder
					.fromMethodName(ImagesController.class,
							"getFile", article.getImagename()).build().toString();
			article.setAbsolutImagename(getFilename);
		});
		/*for (Article article: articles) {
			String getFilename=MvcUriComponentsBuilder
					.fromMethodName(ImagesController.class,
							"getFile", article.getImagename()).build().toString();
			article.setAbsolutImagename(getFilename);
		}*/
		model.addAttribute("articles",articles);
		model.addAttribute("products", products);
		model.addAttribute("count", products.size());
		return "index";
	}
	
	@RequestMapping(value =  "/welcome")
	public String welcome() {
		return "welcome";
	}
	


//	@RequestMapping(value="/login")
//	public String login(Model model) {
//		return "login";
//	}
	
	@RequestMapping(value = "/403")
	public String error403() {
		return "403";
	}
	
//	@GetMapping("/imgfiles/{filename:.+}")
//	@ResponseBody
//	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
//		Resource file = storageService.loadFile(filename);
//		return ResponseEntity
//				.ok()
//				.header(HttpHeaders.CONTENT_DISPOSITION,
//						"attachment; filename=\"" + file.getFilename() + "\"")
//				.body(file);
//	}
}