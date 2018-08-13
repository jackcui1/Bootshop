package com.bootshop.controller.restapi;

import com.bootshop.model.Category;
import com.bootshop.model.SubCategory;
import com.bootshop.service.CategoryService;
import com.bootshop.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Guowei Cui
 * @date 8/12/2018 7:50 AM
 */

@RestController
@RequestMapping("/rest/categories")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryServiceservice;

    @Autowired
    private SubCategoryService subCategoryService;

    @RequestMapping("")
    public List<Category> getAll() {
        return categoryServiceservice.findAll();
    }

    @RequestMapping(value = "/subcategories/{id}")
    public List<SubCategory> getAllSubCategoriesById(@PathVariable String id) {

        List<SubCategory> lists = subCategoryService.findAllByCategoryId(Integer.valueOf(id));
        return lists;

    }

}
