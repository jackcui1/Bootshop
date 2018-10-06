package com.bootshop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bootshop.model.Product;


public interface ProductRepository extends JpaRepository<Product, Integer>{
	Page<Product> findAll(Pageable pageable);
	List<Product> findAll();


	List<Product> findAllBySubCategoryId(Integer id);
	
	
	@Query(value="select * from product p where p.firstcategoryid=?1",nativeQuery=true)
	List<Product> getProductListByFirstCategoryId(int firstCategoryId);
	
	@Query(value="select * from product p where p.secondcategoryid=?1",nativeQuery=true)
	List<Product> getProductListBySecondCategoryId(int secondCategoryId);
	

}
