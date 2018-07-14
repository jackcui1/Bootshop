package com.bootshop.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bootshop.model.Product;
import com.bootshop.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    public ProductRepository repository;

    @Override
    public void add(Product product) {
        repository.save(product);
    }

    @Override
    public Product findById(Integer id) {
        return repository.findOne(id);
    }
//
//    @Override
//    public void delete(Product product) {
//        repository.delete(product);
//    }
//
//    @Override
//    public void deleteById(Integer id) {
//        repository.delete(id);
//    }
//
//    @Override
//    public void update(Product product) {
//        repository.save(product);
//    }
//
//    @Override
//    public Page<Product> findAll(Pageable pageable) {
//        return repository.findAll(pageable);
//    }
//
//    @Override
//    public Page<Product> findByFistCategoryId(Integer id, Pageable pageable) {
//        return repository.findByFirstCategoryId(id, pageable);
//    }
//
//    @Override
//    public Page<Product> findBySecondCategoryId(Integer id, Pageable pageable) {
//        return repository.findBySecondCategoryId(id, pageable);
//    }
//
    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }
//
//    @Override
//    public List<Product> findByFirstCategoryId(Integer id) {
//        return repository.findByFistCategoryId(id);
//    }
//
//    @Override
//    public List<Product> findAllBySecondCategoryId(Integer id) {
//        return repository.findBySecondCategoryId(id);
//    }
//
//    @Override
//    public long Count() {
//        return repository.count();
//    }
}
