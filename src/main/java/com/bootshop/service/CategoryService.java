package com.bootshop.service;

import com.bootshop.model.Category;
import com.bootshop.model.Product;
import com.bootshop.model.SubCategory;

import java.util.List;
import java.util.Set;

/**
 * @author Guowei Cui
 * @date 8/9/2018 3:38 PM
 */


public interface CategoryService {

    List<Category> findAll();
//    List<SubCategory> findAllSubCategoriesById(Integer id);
//    List<SubCategory> findAllBySubCategoriesIs(Integer id);
}
