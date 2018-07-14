package com.bootshop.controller.api;

import com.bootshop.model.Product;
import com.bootshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * product Restful controller
 *
 * @author Guowei Cui
 * @date 7/13/2018 11:07 PM
 */
@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    @Autowired
    private ProductService service;
    @RequestMapping("")
    public List<Product> list() {
        List<Product> lists = new ArrayList<>();
        lists = service.findAll();
        return lists;
    }
}
