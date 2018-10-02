package com.bootshop.controller.restapi;

import com.bootshop.model.Product;
import com.bootshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Guowei Cui
 * @date 8/27/2018 9:50 AM
 */

@RestController
@RequestMapping("/rest/products")
public class ProductRestController {

    @Autowired
    ProductService productService;

    @RequestMapping("/{id}")
    public Product getProductById(@PathVariable("id") int id , Model model ) {
        Product product = productService.getProductById(id);
        return product;
    }
}
