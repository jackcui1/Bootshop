package com.bootshop.service;

import com.bootshop.model.Category;
import com.bootshop.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(CategoryServiceTest.class);

    @Autowired
    CategoryService categoryService;


    @Autowired
    ProductService productService;

    @Test
    @Transactional
    public void findAll() {

        List<Category> lists = categoryService.findAll();
//        logger.info(lists.toString());

    }

}

