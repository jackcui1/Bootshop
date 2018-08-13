package com.bootshop.service.impl;

import com.bootshop.model.Category;
import com.bootshop.model.SubCategory;
import com.bootshop.repository.CategoryRepository;
import com.bootshop.repository.SubCategoryRepository;
import com.bootshop.service.CategoryService;
import com.bootshop.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Guowei Cui
 * @date 8/9/2018 3:39 PM
 */

@Service
@Transactional
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    private SubCategoryRepository repository;

    @Override
    public List<SubCategory> findAllByCategoryId(Integer id) {
        return repository.findAllByCategoryId(id);
    }
}
