package com.bootshop.repository;

import com.bootshop.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Guowei Cui
 * @date 8/9/2018 3:34 PM
 */
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {

    @Modifying
    @Query(value="select * from secondcategory o where o.firstcategoryid = ?1",nativeQuery = true)
    public List<SubCategory> findAllByCategoryId(Integer id);

}
