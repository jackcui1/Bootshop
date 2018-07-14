package com.bootshop.repository;


import com.bootshop.model.FirstCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecondCategoryRepository extends JpaRepository<FirstCategory, Integer> {


}
