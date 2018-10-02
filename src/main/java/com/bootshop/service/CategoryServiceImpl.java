package com.bootshop.service;

import com.bootshop.model.Category;
import com.bootshop.model.SubCategory;
import com.bootshop.repository.CategoryRepository;
import com.bootshop.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

//    @Override
//    public List<SubCategory> findAllSubCategoriesById(Integer id) {
//        return repository.findAllSubCategoriesById(id);
//    }
//
//    @Override
//    public List<SubCategory> findAllBySubCategoriesIs(Integer id) {
//        return repository.findAllBySubCategoriesIs(id);
//    }
}
