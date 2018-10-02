package com.bootshop.service;

import com.bootshop.model.Category;
import com.bootshop.model.SubCategory;

import java.util.List;

/**
 * @author Guowei Cui
 * @date 8/9/2018 3:38 PM
 */


public interface SubCategoryService {

    List<SubCategory> findAllByCategoryId(Integer id);

}
