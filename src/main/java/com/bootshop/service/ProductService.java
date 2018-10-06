package com.bootshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bootshop.model.Product;

public interface ProductService {
	void addProduct(Product product);
	Product getProductById(int id);
	void deleteProduct(Product product);
	void editProduct(Product product);
	Page<Product> getProductList(Pageable pageable);
	List<Product> getProductList();

	List<Product> findAllBySubCategoryId(Integer id);
	
}
