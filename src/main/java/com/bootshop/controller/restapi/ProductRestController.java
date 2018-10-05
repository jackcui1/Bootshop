package com.bootshop.controller.restapi;

import com.bootshop.controller.ImagesController;
import com.bootshop.model.Category;
import com.bootshop.model.Product;
import com.bootshop.service.CategoryService;
import com.bootshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;

/**
 * @author Guowei Cui
 * @date 8/27/2018 9:50 AM
 */

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {

    @Autowired
    ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/{id}")
    public Product getProductById(@PathVariable("id") int id , Model model ) {
        Product product = productService.getProductById(id);
        return product;
    }

    @RequestMapping("")
    public List<Product> getProductList( Model model ) {
        List<Product> products = productService.getProductList();

        for(Product product:products){
            String getFilename= MvcUriComponentsBuilder
                    .fromMethodName(ImagesController.class,
                            "getFile", product.getImagename()).build().toString();
            product.setAbsolutImagename(getFilename);
        }
        System.out.println(products.get(0).getAbsolutImagename());

        return products;

    }
}
