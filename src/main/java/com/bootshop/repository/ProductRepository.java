package com.bootshop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bootshop.model.Product;
import org.springframework.data.jpa.repository.Query;


public interface ProductRepository extends JpaRepository<Product, Integer> {

//    @Query(value = "select * from product p where p.firstcategoryid=?1", nativeQuery = true)
//    Page<Product> findByFirstCategoryId(Integer id, Pageable pageable);
//
//    @Query(value = "select * from product p where p.secondcategoryid=?1", nativeQuery = true)
//    Page<Product> findBySecondCategoryId(Integer id, Pageable pageable);

    @Query(value = "select * from product p where p.firstcategoryid=?1", nativeQuery = true)
    List<Product> findByFistCategoryId(Integer id);

    @Query(value = "select * from product p where p.secondcategoryid=?1", nativeQuery = true)
    List<Product> findBySecondCategoryId(Integer id);


}
