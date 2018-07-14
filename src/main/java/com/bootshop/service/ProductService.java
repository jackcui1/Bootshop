package com.bootshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bootshop.model.Product;

public interface ProductService {


    void add(Product product);

    Product findById(Integer id);
//
//    void delete(Product product);
//
//    void deleteById(Integer id);
//
//    void update(Product product);
//
//    Page<Product> findAll(Pageable pageable);
//
//    Page<Product> findByFistCategoryId(Integer id, Pageable pageable);
//
//    Page<Product> findBySecondCategoryId(Integer id, Pageable pageable);
//
    List<Product> findAll();
//
//    List<Product> findByFirstCategoryId(Integer id);
//
//    List<Product> findAllBySecondCategoryId(Integer id);
//
//    long Count();

}
