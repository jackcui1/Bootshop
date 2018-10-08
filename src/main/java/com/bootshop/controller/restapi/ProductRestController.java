package com.bootshop.controller.restapi;

import com.bootshop.controller.ImagesController;
import com.bootshop.model.Category;
import com.bootshop.model.Product;
import com.bootshop.service.ProductService;
import com.bootshop.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping("/{id}")
    public Product getProductById(@PathVariable("id") int id, Model model) {
        Product product = productService.getProductById(id);
        product.setAbsolutImagename(ImageUtils.imageNameToAbsolutePath(product.getImagename()));
        return product;
    }

    @RequestMapping("")
    public List<Product> getProductList(Model model) {
        List<Product> products = productService.getProductList();

        for (Product product : products) {
            product.setAbsolutImagename(ImageUtils.imageNameToAbsolutePath(product.getImagename()));
        }
        return products;
    }

    @RequestMapping(value = "/subcategories/{id}")
    public List<Product> getProductsBySubCategory(@PathVariable("id") Integer id, Model model) {
        List<Product> products = productService.findAllBySubCategoryId(id);

        for (Product product : products) {
            product.setAbsolutImagename(ImageUtils.imageNameToAbsolutePath(product.getImagename()));
        }

        return products;
    }
}
