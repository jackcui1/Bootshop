package com.bootshop.controller.admin;

import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;

import com.bootshop.model.Product;
import com.bootshop.service.ProductService;
import com.bootshop.service.StorageFileService;

@Controller
@RequestMapping("/admin")
public class ProductAdminController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private StorageFileService storageService;
	List<String> files = new ArrayList<String>();

	@RequestMapping("/productmanagement")
	public String productAdmin(Model model) {
		List<Product> products = productService.getProductList();
		model.addAttribute("products", products);
		return "productmanagement";
	}

	@RequestMapping("/editproduct/{productid}")
	public String showEditProductForm(@PathVariable("productid") int productid,
			Model model) {
		Product product = productService.getProductById(productid);
		model.addAttribute("product", product);
		return "editproduct";
	}

	@RequestMapping(value = "/editproduct", method = RequestMethod.POST)
	public String editProduct(Product product,MultipartFile imageFile) {
		if(imageFile!=null && !imageFile.isEmpty()){
			String fileName = imageFile.getOriginalFilename();
			String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
			
			product.setImagename(product.getProductid()+product.getProductname()+product.getSku()+"."+extensionName);
			
			storageService.store(imageFile,product.getImagename());
		}
		productService.editProduct(product);
		return "redirect:/product/view/" + product.getProductid();
	}

	@RequestMapping(value = "/addproduct", method = RequestMethod.GET)
	public String showAddProductForm(Model model) {
		Product product = new Product();
		product.setAvailability(1);
		model.addAttribute(product);
		return "addproduct";
	}

	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	public String addProduct(Product product,MultipartFile imageFile,Model model) {
		if(imageFile!=null && !imageFile.isEmpty()){
			String fileName = imageFile.getOriginalFilename();
			String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
			
			product.setImagename(product.getProductid()+product.getProductname()+"."+extensionName);
			
			storageService.store(imageFile,product.getImagename());
		}
		productService.addProduct(product);
		return "redirect:/product/view/" + product.getProductid();
	}

	@RequestMapping(value = "/deleteproduct/{productid}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable int productid) {
		productService.deleteProduct(productService.getProductById(productid));
		return "redirect:/admin/productmanagement";
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
