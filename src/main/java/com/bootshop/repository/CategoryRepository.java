package com.bootshop.repository;

import com.bootshop.model.Cart;
import com.bootshop.model.Category;
import com.bootshop.model.SubCategory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Guowei Cui
 * @date 8/9/2018 3:34 PM
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
