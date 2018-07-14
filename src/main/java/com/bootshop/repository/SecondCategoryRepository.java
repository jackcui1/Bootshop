package com.bootshop.repository;


import com.bootshop.model.CartItem;
import com.bootshop.model.FirstCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface FirstCategoryRepository extends JpaRepository<FirstCategory, Integer> {

}
